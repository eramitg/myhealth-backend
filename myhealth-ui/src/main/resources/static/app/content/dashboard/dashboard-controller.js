'use strict';

angular.module('myHealthApp').controller('DashboardController', function ($scope, $http, $state, store) {
    $scope.currentUser = store.get('currentUser');
    $scope.logout = function() {
        console.log('logging out');

        $http.post('/logout').then(function success(response) {
            console.debug('logout success');
            store.remove('currentUser');
            store.remove('jwt');

            $state.go('login', {}, {
                reload : true
            });
        }, function error(response) {
            console.error(response);
        });
    };
});