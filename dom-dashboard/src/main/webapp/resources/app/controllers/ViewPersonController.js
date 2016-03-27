'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewPersonController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var personId = $routeParams.personId;
        $scope.person = {};
        $scope.floor = "N/A";
        $scope.zone = "N/A";
        $scope.desk = "N/A";
        $scope.tmp = "N/A";

        $http({
            method: 'GET',
            url: HOST + '/dynamaps/api/v1/office/person/byid/' + personId
        }).then(function successCallback(response) {
            $scope.person = response.data;
            if ($scope.person.desk) {
                $scope.floor = $scope.person.desk.zone.floor.name;
                $scope.zone = $scope.person.desk.zone.name;
                $scope.desk = $scope.person.desk.id;
            } else if ($scope.person.zone) {
                $scope.floor = $scope.person.zone.floor.name;
                $scope.zone = $scope.person.zone.name;
            }
        });

    });