'use strict';

angular.module('myHealthApp').controller('RegistrationController', function ($scope, $http, registrationService) {
    $scope.registration = {
        email : '',
        terms : false
    };

    $scope.register = function () {
        registrationService.registerUser($scope.registration).then(
            function success(response) {
                $scope.errors = null;
                $scope.registerSuccess = true;
            }, function error(response) {
                $scope.fieldErrors = response.data.fieldErrors;
                $scope.registrationForm.email.$setValidity("sendFailed", response.exception=="org.springframework.dao.IncorrectResultSizeDataAccessException");
            });
    };
});