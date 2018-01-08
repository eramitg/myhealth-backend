'use strict';

angular.module('myHealthApp').controller('BpMeasurementsController', function ($scope, $http, $stateParams, $rootScope, $state) {
    $http.get("/rest/measurement/bp").then(function (response) {
        $scope.bpMeasurements = response.data;
    }, function (response) {
        console.error(response);
    });

    $scope.create = function () {
        $scope.bp = {
            measurement: {
                date: new Date(),
                time: new Date()
            }
        }
    };

    $scope.create();

    $scope.openDatePopup = function () {
        $scope.datePopup.opened = true;
    };

    $scope.datePopup = {
        opened: false
    };

    $scope.currentDate = function () {
        return new Date()
    };

    $scope.saveBp = function () {
        $scope.bp.measurement.date = new Date(
            $scope.bp.measurement.date.getFullYear(),
            $scope.bp.measurement.date.getMonth(),
            $scope.bp.measurement.date.getDate(),
            $scope.bp.measurement.time.getHours(),
            $scope.bp.measurement.time.getMinutes(),
            $scope.bp.measurement.time.getSeconds(),
            $scope.bp.measurement.time.getMilliseconds()
        );
        $scope.bp.measurement.time = null;

        $http.post('/rest/measurement/bp', $scope.bp.measurement).then(function (response) {
            $scope.bp.measurement = response.data;
            $scope.bpMeasurements.push($scope.bp.measurement);
            $scope.isBptSaved = true;
            $scope.create();
        }, function (response) {
            $scope.isBpSaved = false;
        })
    }
});