'use strict';

angular.module('myHealthApp').controller('ActivationController', function ($scope, $http, $stateParams) {
    $http.get('/rest/activation/validate/' + $stateParams.hash)
        .then(function (response) {
            $scope.tokenValid = true;
        }, function (response) {
            $scope.tokenValid = false;
        });

    $scope.activate = function () {
        $http.post('/rest/activate', {
            hash: $stateParams.hash,
            password: $scope.password,
            passwordConfirmation: $scope.passwordConfirmation
        }).then(function (response) {
            $scope.success = true;
        }, function (response) {
            $scope.success = false;
        })
    };
});