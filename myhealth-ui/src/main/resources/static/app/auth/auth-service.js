'use strict';

angular.module('myHealthApp').service('authService', ['$http', function($http, $window) {
    this.parseJwt = function(token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse($window.atob(base64))
    };

    this.saveToken = function(token) {
        $window.localStorage['jwtToken'] = token;
    };

    this.getToken = function() {
        return $window.localStorage['jwtToken'];
    };

    this.isAuthed = function () {
        var token = getToken();
        if (token) {
            var params = parseJwt(token);
            return Math.round(new Date().getTime() / 1000) <= params.exp;
        } else {
            return false;
        }
    };

}]);