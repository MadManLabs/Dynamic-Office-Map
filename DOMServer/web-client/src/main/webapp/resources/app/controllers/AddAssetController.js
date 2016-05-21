'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('AddAssetController', function ($scope, $http, $uibModalInstance, floorId) {

        $scope.assetTypes = [];
        $scope.zones = [];

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.ok = function() {
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
            url: HOST + 'zone/floor/' + floorId
        }).then(function successCallback(response) {
            $scope.zones = response.data;
        });

    });