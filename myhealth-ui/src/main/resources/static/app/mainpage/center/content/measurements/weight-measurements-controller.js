/**
 * Created by KORM004 on 2/20/2017.
 */
'use strict';

angular.module('myHealthApp').controller('WeightMeasurementsController', function ($scope, $http, $stateParams, $rootScope, $state) {
    $http.get("/rest/measurement/weight").then(function (response) {
        $scope.weightMeasurements = response.data;
    }, function (response) {
        console.error(response);
    });


    $scope.weight = {
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

    $scope.saveWeight = function () {
        $http.post('/rest/measurement/weight', $scope.weight.measurement).then(function (response) {
            $scope.weight.measurement = response.data;
            $scope.weightMeasurements.push($scope.weight.measurement);
            $scope.isWeightSaved = true;
        }, function (response) {
            $scope.isWeightSaved = false;
        })
    };
});