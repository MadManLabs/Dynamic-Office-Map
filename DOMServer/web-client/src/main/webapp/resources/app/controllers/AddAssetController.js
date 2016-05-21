'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('AddAssetController', function ($scope, $http, $uibModalInstance, floorId) {

        $scope.assetTypes = [];
        $scope.zones = [];
        $scope.persons = [];
        $scope.assets = [];

        $scope.removeCloseBy = function() {
            $scope.closeBy = {};
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.ok = function() {
            var tenantId = null;
            if ($scope.tenant) {
                tenantId = $scope.tenant.id;
            }

            var closeBy = $scope.closeBy.id;

            var zoneId = null;
            if ($scope.assetZone) {
                zoneId = $scope.assetZone.id;
            }
            var assetTypeId = null;
            if ($scope.assetType) {
                assetTypeId = $scope.assetType.id;
            }

            $scope.asset.typeId = assetTypeId;
            $scope.asset.zoneId = zoneId;
            $scope.asset.tenantId = tenantId;
            $scope.asset.closeByAssetId = closeBy;

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
            url: HOST + 'assetType'
        }).then(function successCallback(response) {
            $scope.assetTypes = response.data;
        });

        $http({
            method: 'GET',
            url: HOST + 'person'
        }).then(function successCallback(response) {
            $scope.persons = response.data;
        });

        $http({
            method: 'GET',
            url: HOST + 'zone/floor/' + floorId
        }).then(function successCallback(response) {
            $scope.zones = response.data;
        });

        $http({
            method: 'GET',
            url: HOST + 'asset/map/' + floorId
        }).then(function successCallback(response) {
            $scope.assets = response.data;
        });

    });