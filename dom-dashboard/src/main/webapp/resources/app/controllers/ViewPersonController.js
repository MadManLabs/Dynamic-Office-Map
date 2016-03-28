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
            url: HOST + '/dynamaps/api/v1/office/person/byid/' + personId
        }).then(function successCallback(response) {
            $scope.person = response.data;

            console.log($scope.person.macZone);

            if ($scope.person.desk) {
                $scope.floor = $scope.person.desk.zone.floor.name;
                $scope.zone = $scope.person.desk.zone.name;
                $scope.desk = $scope.person.desk.id;
            }

            if ($scope.person.zone) {
                $scope.tmp = $scope.person.zone.name + " / " + $scope.person.zone.floor.name;
            }

            if ($scope.person.macZone) {
                $scope.mac = $scope.person.macZone.name + " / " + $scope.person.macZone.floor.name;
            }
        });

    });