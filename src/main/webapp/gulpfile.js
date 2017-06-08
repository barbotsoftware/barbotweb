var gulp = require('gulp');
var $ = require('gulp-load-plugins')({ lazy: true });
var del = require('del');
var runSequence = require('run-sequence');

var config = {
    port: 12345,
    devBaseUrl: 'http://localhost',
    indexUrl: '/app/index.html',
    bowerDir: './bower_components'
}

function errorLog(error) {
    console.error.bind(error);
    console.log(error);
    this.emit('end');
}

gulp.task('init', ['concatjs', 'concatcss', 'connect', 'open', 'watch']);

gulp.task('run', ['concatjs', 'concatcss', 'connect', 'watch']);

gulp.task('connect', function () {
    $.livereload.listen();
    return $.connect.server({
        root: ['./'],
        port: config.port,
        livereload: {
            livereload: true
        }
    });
});

gulp.task('open', function () {
    return gulp.src('app/index.html')
        .pipe($.open({ uri: config.devBaseUrl + ':' + config.port + config.indexUrl }))
});

gulp.task('watch', ['css-watcher', 'js-watcher', 'html-watcher']);

gulp.task('html-watcher', function () {
    return gulp.watch(['app/**/*.html'], { debounceDelay: 6000 }, function () {
        gulp.src('app/index.html')
            .pipe($.livereload())
            .pipe($.notify({ message: 'Reloading HTML' }));
    });
});

gulp.task('js-watcher', function () {
    return gulp.watch(['app/**/*.js'], { debounceDelay: 3000 }, ['concatjs']);
});

gulp.task('concatjs', function () {
    return gulp
        .src([
            // get all 3rd party files
            // 'vendor/**/*.js',
            // read module files first
            'app/**/*.module.js',
            // 'app/app.js',
            //select all javascript files under js/ and any subdirectory
            'app/**/*.js',
            '!app/app.js',
            // exclude tests
            '!app/**/*-spec.js'
        ])
        .pipe($.concat('app.concat.js')) //the name of the resulting file
        .pipe(gulp.dest('build')) //the destination folder
        .pipe($.livereload())
        .pipe($.notify({ message: 'Finished concatenating JavaScript' }));
});


gulp.task('css-watcher', function () {
    return gulp.watch(['app/**/*.css'], { debounceDelay: 3000 }, ['concatcss']);
});

gulp.task('concatcss', function () {
    return gulp.src([
        'app/**/*.css'
    ])
        .pipe($.concat('app.css'))
        .pipe(gulp.dest('build'))
        .pipe($.livereload())
        .pipe($.notify({ message: 'Finished concatenating CSS!' }))
});

/*
Task for Deployment

Deploy PROCESS
- delete any files in deploy folder
- css and concatjs
- get templates and create cache, in templates.js
- concat vendor and core app js w/ template, then ngAnnotate, minify
- delete extra files (templates.js)
- transfer files from img and data
- add index.html that redirects url to proper location
- notify of success :)
*/

// Task for deployment
gulp.task('deploy', function () {
    runSequence('clean-deploy', 'concatcss', 'concatjs', 'compile-deploy-refs', /*'transfer-csv2geojson',*/ 'transfer-typeahead', 'templatecache', 'finalize-deploy-js', 'clean-unneeded', 'transfer-data', 'transfer-folders', 'add-help-page', 'add-redirect', 'notify-completion');
});

// clear deploy folder
gulp.task('clean-deploy', function () {
    del('deploy/*');
});

// grab bower components, concatenate into a single file
gulp.task('compile-deploy-refs', function () {
    return gulp
        .src('app/index.html')
        .pipe($.useref())
        .on('error', errorLog)
        .pipe(gulp.dest('deploy/app'));
});

gulp.task('transfer-csv2geojson', function () {
    return gulp
        .src('vendor/csv2geojson.js')
        .pipe(gulp.dest('deploy/app'));
});

gulp.task('transfer-typeahead', function () {
    return gulp
        .src('vendor/typeahead.bundle.js')
        .pipe(gulp.dest('deploy/app'));
});

// create template cache file of all html templates
gulp.task('templatecache', function () {
    return gulp
        .src('app/**/*.html')
        .pipe($.minifyHtml({ empty: true }))
        .pipe($.angularTemplatecache('templates.js',
            {
                module: 'Global300',
                standAlone: false,
                root: ''
            }))
        .pipe(gulp.dest('deploy/app'));
});

// concatenate all js files in deploy, annotate, uglify
gulp.task('finalize-deploy-js', function () {
    return gulp
        .src(['deploy/app/**/*.js', '!deploy/app/csv2geojson.js'])
        .pipe($.concat('app.min.js'))
        .pipe($.ngAnnotate())
        .pipe($.uglify())
        .on('error', errorLog)
        .pipe(gulp.dest('deploy/app'));
});

// remove unnecessary files
gulp.task('clean-unneeded', function () {
    del(['deploy/app/**/*.js', '!deploy/app/app.min.js', '!deploy/app/csv2geojson.js', '!deploy/app/typeahead.bundle.js']);
});

gulp.task('transfer-folders', ['transfer-img']);

gulp.task('transfer-img', function () {
    return gulp
        .src('images/**/*')
        .pipe(gulp.dest('deploy/images'));
});

gulp.task('transfer-data', function () {
    return gulp
        .src('data/*')
        .pipe(gulp.dest('deploy/data'));
});

gulp.task('add-help-page', function () {
    return gulp
        .src('app/info.html')
        .pipe(gulp.dest('deploy/app/'));
});

gulp.task('add-redirect', function () {
    return gulp
        .src('redirect/index.html')
        .pipe(gulp.dest('deploy'));
});

gulp.task('notify-completion', function () {
    $.notify({ message: 'Done Deploying!' });
});
