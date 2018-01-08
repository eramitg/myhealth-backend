'use strict';

angular.module('myHealthApp').controller('LoginController', function ($scope, $http, $state, $rootScope, store) {
    $scope.login = function () {
        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            }
        };

        var o = {
            email: $scope.email,
            password: $scope.password
        };

        $http.post('/login', o, config).then(
            function success(response) {
                store.set('jwt', response.headers('Authorization'));
                store.set('currentUser', o.email);

                $rootScope.currentUser = {
                    email: $scope.email
                };

                $state.go('protected.home', {}, {
                    reload: true
                });
            }, function error(response) {
                console.error(response);
                $scope.error = response.data.messageCode;
            });
    }
});