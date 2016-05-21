'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('AddItemController', function ($scope, $http, $uibModalInstance, NotificationService, floorId, canvas) {

        $scope.objectTypes = ['Zone', 'Asset', 'Rectangle', 'Text'];
        $scope.colors = ['red', 'green', 'blue'];
        $scope.zones = [];
        $scope.assetTypes = [];

        var grid = 50;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.ok = function() {
            var object = null;
            if ($scope.objectType === 'Rectangle') {
                object = addRectangle();
            } else if ($scope.objectType === 'Text') {
                object = addText();
            } else if ($scope.objectType === 'Zone') {
                addZone();
            } else if ($scope.objectType === 'Asset') {
                addAsset();
            }
            $uibModalInstance.close(object);
        };

        var addAsset = function() {
            var zoneId = null;
            if ($scope.assetZone) {
                zoneId = $scope.assetZone.id;
            }
            var assetTypeId = null;
            var assetImage = "resources/image/asset.png";
            if ($scope.assetType) {
                assetTypeId = $scope.assetType.id;

                if ($scope.assetType.image) {
                    assetImage = $scope.assetType.image;
                }
            }
            $http({
                method: 'POST',
                url: HOST + 'asset',
                data: {
                    name: $scope.assetName,
                    code: $scope.assetCode,
                    typeId: assetTypeId,
                    zoneId: zoneId,
                    onMap: true
                }
            }).then(function successCallback(response) {
                $scope.zones.push(response.data);
                fabric.Image.fromURL(assetImage, function(image) {
                    var zone = image.set({
                        left: 100,
                        top: 100,
                        hasControls: false,
                        hasRotatingPoint: false,
                        objectType: 'Asset',
                        idObject: response.data.id
                    });
                    canvas.add(zone);
                });
                NotificationService.info('The new asset was added.');
            });
        };

        var addZone = function() {
            var parentZoneId = null;
            if ($scope.parentZone !== undefined) {
                parentZoneId = $scope.parentZone.id;
            }
            $http({
                method: 'POST',
                url: HOST + 'zone',
                data: {
                    name: $scope.zoneName,
                    code: $scope.zoneCode,
                    parentId: parentZoneId,
                    floorId: floorId
                }
            }).then(function successCallback(response) {
                $scope.zones.push(response.data);
                fabric.Image.fromURL('resources/image/pin.png', function(image) {
                    var zone = image.set({
                        left: 100,
                        top: 100,
                        hasControls: false,
                        hasRotatingPoint: false,
                        objectType: 'Zone',
                        zoneName: $scope.zoneName,
                        idObject: response.data.id
                    });
                    canvas.add(zone);
                });
                NotificationService.info('The new zone was added.');
            });
        };

        var addText = function() {
            return new fabric.Text($scope.text, {
                left: 100,
                top: 100,
                objectType: 'Text'
            });
        };

        var addRectangle = function() {
            return new fabric.Rect({
                left: 100,
                top: 100,
                fill: $scope.selectedColor,
                width: grid,
                height: grid,
                objectType: 'Rectangle'
            });
        };

        $http({
            method: 'GET',
            url: HOST + 'zone/floor/' + floorId
        }).then(function successCallback(response) {
            $scope.zones = response.data;
        });

        $http({
            method: 'GET',
            url: HOST + 'assetType'
        }).then(function successCallback(response) {
            $scope.assetTypes = response.data;
        });

    });