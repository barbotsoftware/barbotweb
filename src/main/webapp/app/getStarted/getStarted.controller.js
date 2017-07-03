angular.module('barbot')
    .controller('GetStartedController', ['$scope', '$http', function($scope, $http) {
        $scope.title = 'Get Started';

        $scope.fields = [
            {
                type: 'text',
                name: 'Name'
            },
            {
                type: 'email',
                name: 'Email'
            },
            {
                type: 'text',
                name: 'City'
            }
        ];

        $scope.inputs = {
            customFields: {
                'Name': '',
                'Email': '',
                'City': ''
            }
        };

        $scope.submit = function() {
            var data = {
                name: $scope.inputs.customFields['Name'],
                email: $scope.inputs.customFields['Email'],
                city: $scope.inputs.customFields['City']
            };

            $http.post('/signup', data)
                .then(function(data, status, headers, config) {
                    console.log(data);
                });
        };
    }]);