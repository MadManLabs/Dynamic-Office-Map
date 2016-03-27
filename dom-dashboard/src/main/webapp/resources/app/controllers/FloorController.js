'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('FloorController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};

        $scope.objectTypes = ['Zone', 'Desk', 'Rectangle', 'Text'];
        $scope.colors = ['red', 'green', 'blue'];
        $scope.zones = [];

        $scope.objectSelected = false;
        $scope.objectSelectedInfo = {};

        var canvas = new fabric.Canvas('floorMap', { selection: false });
        var grid = 50;
        var gridSize = 1100;

        $scope.addObject = function(object) {
            if (object.type === 'Rectangle') {
                var rec = new fabric.Rect({
                    left: 100,
                    top: 100,
                    fill: $scope.selectedColor,
                    width: grid,
                    height: grid
                });
                canvas.add(rec);
            } else if (object.type === 'Desk') {
                if (!object.zone) {
                    alert("Please provide the desk code.");
                    return;
                }
                $http({
                    method: 'POST',
                    url: HOST + '/dynamaps/api/v1/office/desk',
                    data: {
                        zone: {
                            id: object.zone
                        }
                    }
                }).then(function successCallback(response) {
                    fabric.Image.fromURL('resources/image/pc.png', function(img) {
                        var desk = img.set({
                            left: 100,
                            top: 100,
                            hasControls: false,
                            hasRotatingPoint: false,
                            objectType: 'Desk',
                            zone: object.zone,
                            idObject: response.data.id
                        });
                        canvas.add(desk);
                        $scope.saveFloor();
                    });
                    NotificationService.info('The new desk was added.');
                });
            } else if (object.type === 'Text') {
                var text = new fabric.Text(object.text, {
                    left: 100,
                    top: 100,
                    objectType: 'Text'
                });
                canvas.add(text);
            } else if (object.type === 'Zone') {
                if (!object.name) {
                    alert("Please provide the zone name.");
                    return;
                }
                $http({
                    method: 'POST',
                    url: HOST + '/dynamaps/api/v1/office/zone',
                    data: {
                        name: object.name,
                        floor: {
                            id: floorId
                        }
                    }
                }).then(function successCallback(response) {
                    $scope.zones.push(response.data);
                    fabric.Image.fromURL('resources/image/pin.png', function(img) {
                        var fig = img.set({
                            left: 100,
                            top: 100,
                            hasControls: false,
                            hasRotatingPoint: false,
                            objectType: 'Zone',
                            zoneName: object.name,
                            idObject: response.data.id
                        });
                        canvas.add(fig);
                        $scope.saveFloor();
                    });
                    NotificationService.info('The new zone was added.');
                });
            }
        };

        $scope.deleteObject = function() {
            var activeObject = canvas.getActiveObject(),
                activeGroup = canvas.getActiveGroup();
            if (activeObject) {
                if (confirm('Are you sure?')) {
                    canvas.remove(activeObject);
                }
            } else if (activeGroup) {
                if (confirm('Are you sure?')) {
                    var objectsInGroup = activeGroup.getObjects();
                    canvas.discardActiveGroup();
                    objectsInGroup.forEach(function(object) {
                        canvas.remove(object);
                    });
                }
            }
        };

        $scope.saveFloor = function() {
            var map = JSON.stringify(canvas.toJSON(['idObject', 'zoneName', 'objectType', 'zone']));
            $scope.floor.map = map;
            $http({
                method: 'POST',
                url: HOST + '/dynamaps/api/v1/office/floor',
                data: $scope.floor
            }).then(function successCallback(response) {
                NotificationService.info('The floor was updated.');
            });
        };

        $scope.updateDesk = function() {
            $http({
                method: 'POST',
                url: HOST + '/dynamaps/api/v1/office/desk',
                data: {
                    id: $scope.objectSelectedInfo.idObject,
                    zone: {
                        id: $scope.objectSelectedInfo.zone
                    }
                }
            }).then(function successCallback(response) {
                $scope.saveFloor();
                NotificationService.info('The desk was updated.');
            });
        };

        $scope.updateZone = function() {
            $http({
                method: 'POST',
                url: HOST + '/dynamaps/api/v1/office/zone',
                data: {
                    id: $scope.objectSelectedInfo.idObject,
                    name: $scope.objectSelectedInfo.zoneName,
                    floor: {
                        id: floorId
                    }
                }
            }).then(function successCallback(response) {
                $scope.saveFloor();
                NotificationService.info('The zone was updated.');
            });
        };

        $http({
            method: 'GET',
            url: HOST + '/dynamaps/api/v1/office/floor/' + floorId + '/zone'
        }).then(function successCallback(response) {
            if (response.data) {
                $scope.zones = response.data;
            }
        });

        $http({
            method: 'GET',
            url: HOST + '/dynamaps/api/v1/office/floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            if ($scope.floor.map) {
                //console.log($scope.floor.map);
                canvas.loadFromJSON($scope.floor.map, canvas.renderAll.bind(canvas), function(o, object) {
                    if (object.objectType && (object.objectType === 'Zone' || object.objectType === 'Desk')) {
                        object.hasControls = false;
                        object.hasRotatingPoint = false;
                    }
                });
            } else {
                for (var i = 0; i < (gridSize / grid); i++) {
                    canvas.add(new fabric.Line([ i * grid, 0, i * grid, gridSize], { stroke: '#ccc', selectable: false }));
                    canvas.add(new fabric.Line([ 0, i * grid, gridSize, i * grid], { stroke: '#ccc', selectable: false }))
                }
            }

            canvas.on('object:moving', function(options) {
                if (options.target.objectType && (options.target.objectType === 'Zone' || options.target.objectType === 'Text')) {
                    return;
                }
                options.target.set({
                    left: Math.round(options.target.left / grid) * grid,
                    top: Math.round(options.target.top / grid) * grid
                });
            });

            canvas.on('object:selected', function(object) {
                if (object.target.objectType && (object.target.objectType === 'Zone' || object.target.objectType === 'Desk')) {
                    console.log(object.target);
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

    });