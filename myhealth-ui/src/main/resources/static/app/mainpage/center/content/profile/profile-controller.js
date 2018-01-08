'use strict';

angular.module('myHealthApp').controller('ProfileController', function ($scope, $http, $stateParams, $rootScope, $state) {
    $http.get('/rest/person').then(function(response) {
       $scope.profile = response.data;
    });

    $scope.openBirthDatePopup = function () {
        $scope.birthDatePopup.opened = true;
    };

    $scope.birthDatePopup = {
        opened: false
    };

    $scope.currentDate = function () {
        return new Date();
    };

    $scope.save = function () {
        $http.post('/rest/person', $scope.profile).then(function (response) {
            $scope.profile = response.data;
            $scope.isProfileSaved = true;
        }, function (response) {
            $scope.isProfileSaved = false;
        })
    }
});