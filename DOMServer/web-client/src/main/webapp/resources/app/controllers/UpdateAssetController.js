'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('UpdateAssetController', function ($scope, $http, $uibModalInstance, assetId) {

        $scope.asset = {};
        $scope.zones = [];
        $scope.floor = {};
        $scope.zone = {};

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.ok = function() {
            if ($scope.zone) {
                $scope.asset.zoneId = $scope.zone.id;
            }

            $http({
                method: 'POST',
                url: HOST + 'asset',
                data: $scope.asset
            }).then(function successCallback(response) {
                $uibModalInstance.close(response.data);
            });
        };

        $http({
            method: 'GET',
            url: HOST + 'asset/' + assetId
        }).then(function successCallback(response) {
            $scope.asset = response.data;

            $http({
                method: 'GET',
                url: HOST + 'zone/floor/' + $scope.asset.floorId
            }).then(function successCallback(response) {
                $scope.zones = response.data;
            });

            $http({
                method: 'GET',
                url: HOST + 'floor/' + $scope.asset.floorId
            }).then(function successCallback(response) {
                $scope.floor = response.data;
            });

            $http({
                method: 'GET',
                url: HOST + 'zone/' + $scope.asset.zoneId
            }).then(function successCallback(response) {
                $scope.zone = response.data;
            });
        });

    });