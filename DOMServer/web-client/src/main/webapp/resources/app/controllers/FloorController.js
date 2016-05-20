'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('FloorController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};
        var grid = 50;

        var canvas = new fabric.CanvasWithViewport('floorMap', { selection: false });

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

        $http({
            method: 'GET',
            url: HOST + 'floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            if ($scope.floor.map) {
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
                if (object.target.objectType && (object.target.objectType === 'Zone' || object.target.objectType === 'Desk')) {
                    $scope.$apply(function() {
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = object.target;
                    });
                } else {
                    $scope.$apply(function() {
                        $scope.objectSelected = false;
                    });
                }
            });

            canvas.on('selection:cleared', function(object) {
                $scope.$apply(function() {
                    $scope.objectSelected = false;
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