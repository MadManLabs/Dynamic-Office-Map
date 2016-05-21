'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('UpdateFloorController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};
        $scope.objectSelected = false;
        $scope.objectSelectedInfo = null;
        $scope.objectSelectedDBInfo = {};
        var grid = 50;

        var canvas = new fabric.CanvasWithViewport('floorMap', { selection: false });

        var updateObjectSelectedDBInfo = function() {
            var url = undefined;
            if ($scope.objectSelectedInfo !== null && $scope.objectSelectedInfo.objectType === 'Asset') {
                url = HOST + "asset/" + $scope.objectSelectedInfo.idObject;
            } else if ($scope.objectSelectedInfo !== null && $scope.objectSelectedInfo.objectType === 'Zone') {
                url = HOST + "zone/" + $scope.objectSelectedInfo.idObject;
            }
            if (url) {
                $http({
                    method: 'GET',
                    url: url
                }).then(function successCallback(response) {
                    $scope.objectSelectedDBInfo = response.data;
                });
            }
        };

        $scope.updateItem = function() {
            if ($scope.objectSelectedInfo !== null && $scope.objectSelectedInfo.objectType === 'Asset') {
                updateAsset();
            } else if ($scope.objectSelectedInfo !== null && $scope.objectSelectedInfo.objectType === 'Zone') {
                updateZone();
            }
        };

        var updateZone = function() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/update_zone.html',
                controller: 'UpdateZoneController',
                resolve: {
                    zoneId: function() {
                        return $scope.objectSelectedInfo.idObject;
                    }
                }
            });
        };

        var updateAsset = function() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/update_asset.html',
                controller: 'UpdateAssetController',
                resolve: {
                    assetId: function() {
                        return $scope.objectSelectedInfo.idObject;
                    }
                }
            });
        };

        $scope.addItem = function() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/add_item.html',
                controller: 'AddItemController',
                resolve: {
                    floorId: function() {
                        return floorId;
                    },
                    canvas: function() {
                        return canvas;
                    }
                }
            });

            modalInstance.result.then(function (object) {
                if (object != null) {
                    canvas.add(object);
                }
                $scope.saveFloor();
            });
        };

        $scope.saveFloor = function() {
            $scope.floor.map = JSON.stringify(canvas.toJSON(['idObject', 'zoneName', 'objectType', 'zone']));
            $http({
                method: 'POST',
                url: HOST + 'floor',
                data: $scope.floor
            }).then(function successCallback(response) {
                NotificationService.info('The floor was updated.');
            });
        };

        $scope.deleteObject = function() {
            var activeObject = canvas.getActiveObject();
            if (activeObject) {
                if (confirm('Are you sure?')) {
                    if ($scope.objectSelectedInfo !== null && $scope.objectSelectedInfo.objectType === 'Asset') {
                        $http({
                            method: 'PUT',
                            url: HOST + 'asset/' + $scope.objectSelectedInfo.idObject + '/removeFromMap'
                        }).then(function successCallback(response) {
                            canvas.remove(activeObject);
                        });
                    } else {
                        canvas.remove(activeObject);
                    }
                }
            }
        };

        $http({
            method: 'GET',
            url: HOST + 'floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            String.prototype.replaceAll = function(search, replacement) {
                var target = this;
                return target.replace(new RegExp(search, 'g'), replacement);
            };

            if ($scope.floor.map) {

                $scope.floor.map = $scope.floor.map.replaceAll('localhost', window.location.hostname);
                $scope.floor.map = $scope.floor.map.replace( /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/gi, window.location.hostname);

                canvas.loadFromJSON($scope.floor.map, canvas.renderAll.bind(canvas), function(o, object) {
                    if (object.objectType && (['Zone', 'Asset'].indexOf(object.objectType) != -1)) {
                        object.hasControls = false;
                        object.hasRotatingPoint = false;
                    }
                });
            }

            canvas.on('object:moving', function(options) {
                if (options.target.objectType && (['Zone', 'Text', 'Rectangle'].indexOf(options.target.objectType) != -1)) {
                    return;
                }
                options.target.set({
                    left: Math.round(options.target.left / grid) * grid,
                    top: Math.round(options.target.top / grid) * grid
                });
            });

            canvas.on('object:selected', function(object) {
                if (object.target.objectType && (['Zone', 'Asset'].indexOf(object.target.objectType) != -1)) {
                    $scope.$apply(function() {
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = object.target;
                        $scope.objectSelectedDBInfo = {};
                        updateObjectSelectedDBInfo();
                    });
                } else {
                    $scope.$apply(function() {
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = null;
                        $scope.objectSelectedDBInfo = {};
                    });
                }
            });

            canvas.on('selection:cleared', function(object) {
                $scope.$apply(function() {
                    $scope.objectSelected = false;
                    $scope.objectSelectedInfo = null;
                    $scope.objectSelectedDBInfo = {};
                });
            });

        });

        $scope.grabMode = function(grabMode) {
            canvas.isGrabMode = grabMode;
        };

        $scope.zoomIn = function() {
            canvas.setZoom(canvas.viewport.zoom * 1.1); // zoom in by 10%
        };

        $scope.zoomOut = function() {
            canvas.setZoom(canvas.viewport.zoom / 1.1); // zoom out by 10%
        };

    });