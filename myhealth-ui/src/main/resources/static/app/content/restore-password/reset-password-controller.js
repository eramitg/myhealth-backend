'use strict';

angular.module('myHealthApp').controller('ResetPasswordController', function ($scope, $http, $stateParams, $rootScope, $state) {
    $http.get('/rest/password/reset/validate', {
        params : {
            hash : $stateParams.hash
        }
    }).then(function(response) {
        $scope.tokenValid = true;
    }, function(response) {
        $scope.tokenValid = false;
    });

    $scope.reset = function() {
        var form = {
            hash : $stateParams.hash,
            newPassword : $scope.password,
            newPasswordConfirmation : $scope.passwordConfirmation
        };

        $http.post('/rest/password/reset/execute', form).then(function success(response) {
            $scope.success = true;

            var config = {
                headers : {
                    'Content-Type' : 'application/x-www-form-urlencoded'
                },
                transformRequest : function(obj) {
                    var str = [];
                    for ( var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                }
            };

            var userCredentials = {
                email : response.data.email,
                password : $scope.password
            };

            $http.post('/login', userCredentials, config).then(function success(response) {
                $rootScope.currentUser = {
                    email: userCredentials.email
                };

                $state.go('protected.home', {}, {
                    reload : true
                });
            }, function error(response) {

            });

        }, function error(response) {

            $scope.success = false;

        });
    };

});