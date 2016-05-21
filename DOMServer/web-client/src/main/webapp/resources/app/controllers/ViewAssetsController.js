'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewAssetsController', function ($scope, $http, $uibModal, $routeParams, $window) {

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

        $scope.viewQRCode = function(assetId) {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/view_qrcode.html',
                controller: 'ViewQRCodeController',
                size: "sm",
                resolve: {
                    assetId: function() {
                        return assetId;
                    },
                    type: function() {
                        return "asset";
                    }
                }
            });
        };

        $scope.editAsset = function(assetId) {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/update_asset.html',
                controller: 'UpdateAssetController',
                resolve: {
                    assetId: function() {
                        return assetId;
                    },
                    viewTenant: function () {
                        return true;
                    }
                }
            });

            modalInstance.result.then(function (asset) {
                $window.location.reload();
            });
        };

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