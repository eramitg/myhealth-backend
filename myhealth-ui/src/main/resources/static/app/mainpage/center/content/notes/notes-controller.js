'use strict';

angular.module('myHealthApp').controller('NoteController', function ($scope, $http) {
    $http.get("/rest/notes").then(function (response) {
        $scope.notes = response.data;
    }, function (response) {
        console.error(response);
    });

    $scope.create = function () {
        $scope.note = {
            date: new Date(),
            time: new Date()
        }
    };

    $scope.create();

    $scope.openDatePopup = function () {
        $scope.datePopup.opened = true;
    };

    $scope.datePopup = {
        opened: false
    };

    $scope.currentDate = function () {
        return new Date()
    };

    $scope.save = function () {
        $scope.note.date = new Date(
            $scope.note.date.getFullYear(),
            $scope.note.date.getMonth(),
            $scope.note.date.getDate(),
            $scope.note.time.getHours(),
            $scope.note.time.getMinutes(),
            $scope.note.time.getSeconds(),
            $scope.note.time.getMilliseconds()
        );
        $scope.note.time = null;

        $http.post('/rest/note', $scope.note).then(function (response) {
            $scope.note = response.data;
            $scope.notes.push($scope.note);
            $scope.isSaved = true;
            $scope.create();
        }, function (response) {
            $scope.isSaved = false;
        })
    }
});