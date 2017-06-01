'use strict';
var app = angular.module('barbot', []);

app.controller("MainController", ['$scope', 'headerService', function($scope, headerService) {
    // Anything you wanna see in the html, put in $scope
    $scope.anus = "anus (from MainController)";

    var counter = 0;
    $scope.anusCounter = function() {
        counter++;
        $scope.anus = "anus (from MainController) " + counter;
    }

    var test = headerService.getAnus();
    $scope.anusArray = ["anus 1", "anus 2", "anus 3"];

    $scope.test = test;
}]);