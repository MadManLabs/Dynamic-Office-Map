'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('UpdateZoneController', function ($scope, $http, $uibModalInstance, zoneId) {

        $scope.zone = {};
        $scope.zones = [];
        $scope.parent = {};

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.ok = function() {
            $scope.zone.parentId = $scope.parent.id;
            console.log($scope.parent);
            console.log($scope.zone);
            $http({
                method: 'POST',
                url: HOST + 'zone',
                data: $scope.zone
            }).then(function successCallback(response) {
                $uibModalInstance.close(response.data);
            });
        };

        $scope.remove = function() {
            $scope.parent = {};
        };

        $http({
            method: 'GET',
            url: HOST + 'zone/' + zoneId
        }).then(function successCallback(response) {
            $scope.zone = response.data;

            $http({
                method: 'GET',
                url: HOST + 'zone/floor/' + $scope.zone.floorId
            }).then(function successCallback(response) {
                $scope.zones = response.data.filter(function (zone) { return zone.id !== zoneId; });
            });

            if ($scope.zone.parentId) {
                $http({
                    method: 'GET',
                    url: HOST + 'zone/' + $scope.zone.parentId
                }).then(function successCallback(response) {
                    $scope.parent = response.data;
                });
            }

        });

    });