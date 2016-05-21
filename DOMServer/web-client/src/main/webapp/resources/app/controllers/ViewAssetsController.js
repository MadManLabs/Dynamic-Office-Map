'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewAssetsController', function ($scope, $http, $uibModal, $routeParams, $location) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};
        $scope.assets = [];

        $http({
            method: 'GET',
            url: HOST + 'floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;
        });

        $http({
            method: 'GET',
            url: HOST + 'asset/floor/' + floorId
        }).then(function successCallback(response) {
            $scope.assets = response.data;
        });

        $scope.addAsset = function() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/add_asset.html',
                controller: 'AddAssetController',
                resolve: {
                    floorId: function() {
                        return floorId;
                    }
                }
            });

            modalInstance.result.then(function (asset) {
                $scope.assets.push(asset);
            });
        };

        $scope.addAssetType = function() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/add_asset_type.html',
                controller: 'AddAssetTypeController'
            });
        };

    });