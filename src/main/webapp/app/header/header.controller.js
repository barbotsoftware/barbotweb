angular.module('barbot')
    .controller('HeaderController', ['$scope', '$location', '$anchorScroll', function($scope, $location, $anchorScroll) {
        $scope.logo = {
            src: 'http://localhost:12345/app/header/images/logo.png',   // TODO: change absolute URL
            name: 'logo'
        };

        $scope.title = 'BarBot';

        $scope.navBarLinks = [
            {
                scrollToId: 'features',
                linkText: 'Features'
            },
            {
                scrollToId: 'howItWorks',
                linkText: 'How It Works'
            },
            {
                scrollToId: 'getStarted',
                linkText: 'Get Started'
            }
        ];

        // scroll to position on page
        $scope.scrollTo = function(pos) {
            $location.hash(pos);

            $anchorScroll();
        }
    }]);