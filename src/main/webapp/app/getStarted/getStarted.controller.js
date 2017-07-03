angular.module('barbot')
    .controller('GetStartedController', ['$scope', function($scope) {
        $scope.title = 'Get Started';

        $scope.inputs = [
            {
                type: 'text',
                id: 'nameInput',
                placeholder: 'Name'
            },
            {
                type: 'email',
                id: 'emailInput',
                placeholder: 'Email'
            },
            {
                type: 'text',
                id: 'cityInput',
                placeholder: 'City'
            }
        ];
    }]);