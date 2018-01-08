'use strict';

angular.module('myHealthApp').controller('ForgotPasswordController', function ($scope, $http) {
    $scope.restore = function () {
        $http.post('/rest/password/restore', {
            email : $scope.restorePasswordForm.email
        }).then(function success(response) {
            console.debug('forgot password success', response);
            $scope.success = true;
            $scope.errors = null;
        }, function error(response) {
            console.error(response);

            $scope.success = false;
            $scope.errors = {};
            angular.forEach(response.data.errors, function(error, idx) {
                $scope.errors[error.field] = error.code;
            });
        });
    };
});