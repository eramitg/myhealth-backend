'use strict';

angular.module('myHealthApp').controller('HeightMeasurementsController', function ($scope, $http, $stateParams, $rootScope, $state) {
    $http.get("/rest/measurement/height").then(function (response) {
        $scope.heightMeasurements = response.data;
    }, function (response) {
        console.error(response);
    });


    $scope.height = {
        measurement : {
            date: new Date()
        }
    };

    $scope.openDatePopup = function () {
        $scope.datePopup.opened = true;
    };

    $scope.datePopup = {
        opened: false
    };

    $scope.currentDate = function () {
        return new Date();
    };

    $scope.saveHeight = function () {
        $http.post('/rest/measurement/height', $scope.height.measurement).then(function (response) {
            $scope.height.measurement = response.data;
            $scope.heightMeasurements.push($scope.height.measurement);
            $scope.isHeightSaved = true;
        }, function (response) {
            $scope.isHeightSaved = false;
        })
    };
});