'use strict';

angular.module('myHealthApp').service('registrationService', ['$http', function($http) {
    this.registerUser = function(registrationForm) {
        return $http.post('/rest/registration', registrationForm);
    }
}]);