'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('UpdateAssetController', function ($scope, $http, $uibModalInstance, assetId, viewTenant) {

        $scope.asset = {};
        $scope.zones = [];
        $scope.floor = {};
        $scope.zone = {};

        $scope.persons = [];
        if (viewTenant) {
            $scope.viewTenant = true;
        }
        $scope.tenant = {};

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.removeTenant = function() {
            $scope.tenant = {};
        };

        $scope.ok = function() {
            if ($scope.zone) {
                $scope.asset.zoneId = $scope.zone.id;
            }

            if (viewTenant) {
                $scope.asset.tenantId = $scope.tenant.id;
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

            if (viewTenant) {
                $http({
                    method: 'GET',
                    url: HOST + 'person'
                }).then(function successCallback(response) {
                    $scope.persons = response.data;
                });

                if ($scope.asset.tenantId) {
                    $http({
                        method: 'GET',
                        url: HOST + 'person/id/' + $scope.asset.tenantId
                    }).then(function successCallback(response) {
                        $scope.tenant = response.data;
                    });
                }
            }

        });

    });