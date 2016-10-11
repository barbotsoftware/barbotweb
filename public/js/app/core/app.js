angular.module('barbot', [], function($interpolateProvider) {
    $interpolateProvider.startSymbol('({');
    $interpolateProvider.endSymbol('})');
});