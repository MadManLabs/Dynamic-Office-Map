'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewPersonController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var personId = $routeParams.personId;
        $scope.person = {};
        $scope.floor = "N/A";
        $scope.zone = "N/A";
        $scope.desk = "N/A";
        $scope.tmp = "N/A";

        $scope.mac = "N/A";

        $http({
            method: 'GET',
            url: HOST + 'person/id/' + personId
        }).then(function successCallback(response) {
            $scope.person = response.data;

            if ($scope.person.permanentFloorName) {
                $scope.floor = $scope.person.permanentFloorName;
            }

            if ($scope.person.permanentZoneName) {
                $scope.zone = $scope.person.permanentZoneName;
            }

            if ($scope.person.permanentDeskCode) {
                $scope.desk = $scope.person.permanentDeskCode;
            }

            if ($scope.person.temporaryZoneName || $scope.person.temporaryFloorName) {
                $scope.tmp = $scope.person.temporaryZoneName + " / " + $scope.person.temporaryFloorName;
            }

            if ($scope.person.macZone) {
                $scope.mac = $scope.person.macZone.name + " / " + $scope.person.macZone.floor.name;
            }
        });

    });