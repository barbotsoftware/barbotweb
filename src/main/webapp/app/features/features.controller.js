angular.module('barbot')
    .controller('FeaturesController', ['$scope', function($scope) {
        $scope.title = 'Features';

        $scope.features = [
            {
                name: 'Cup Dispenser',
                description: ''
            },
            {
                name: 'Ice',
                description: ''
            },
            {
                name: 'Garnish',
                description: ''
            },
            {
                name: 'Refrigeration',
                description: ''
            }
        ];
    }]);