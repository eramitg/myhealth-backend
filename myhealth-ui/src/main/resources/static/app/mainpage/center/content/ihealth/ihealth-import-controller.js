'use strict';

angular.module('myHealthApp').controller('IHealthImportController', function ($scope, store, $http, $window, $state, $stateParams) {
    if ($state.redirect) {
        $scope.importStatus = 'Import is in process';
        $scope.code = $stateParams.code;
        $scope.id = $stateParams.id;

        $http.get('/rest/ihealth/redirect_for_code', {params: {code: $scope.code, id: $scope.id}}).then(function(response) {
            console.log('response');
        }, function(response) {
            console.error(response);
        });

    } else {
        $scope.importStatus = '';
        $scope.importStatus = '';
    }
    $scope.currentUser = store.get('currentUser');

    $scope.import = function() {
        var user = {
            email: $scope.currentUser
        };

        $http.post('/rest/ihealth/authorizationUrl', user).then(function(response) {
            $window.open(response.data.url, 'ihealth', 'toolbar=no, scrollbars=no, resizable=no, top=100, left=500, width=500, height=500');
        }, function(response) {
            console.log(response);
        })
    }
});