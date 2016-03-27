'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewFloorController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};

        $scope.objectSelected = false;
        $scope.objectSelectedInfo = {};

        $scope.person = {};
        $scope.persons = [];

        var grid = 50;
        var gridSize = 1100;

        var canvas = new fabric.Canvas('floorMap', { selection: false });

        $http({
            method: 'GET',
            url: HOST + '/dynamaps/api/v1/office/floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            if ($scope.floor.map) {
                canvas.loadFromJSON($scope.floor.map, canvas.renderAll.bind(canvas), function(o, object) {
                    if (object.objectType && (object.objectType === 'Zone' || object.objectType === 'Desk')) {
                        object.hasControls = false;
                        object.hasRotatingPoint = false;
                        object.lockMovementX = true;
                        object.lockMovementY = true;
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
                if (object.target.objectType && object.target.objectType === 'Desk') {
                    console.log('selected desk');
                    $scope.persons = [];
                    $scope.$apply(function () {
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = object.target;
                    });
                    $http({
                        method: 'GET',
                        url: HOST + '/dynamaps/api/v1/office/person/' + object.target.idObject + '/desk'
                    }).then(function successCallback(response) {
                        console.log(response.data);
                        $scope.persons.push(response.data);
                    });
                } else if (object.target.objectType && object.target.objectType === 'Zone') {
                    console.log('selected zone');
                    $scope.persons = [];
                    $scope.$apply(function() {
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = object.target;
                    });
                    $http({
                        method: 'GET',
                        url: HOST + '/dynamaps/api/v1/office/person/' + object.target.idObject + '/zone'
                    }).then(function successCallback(response) {
                        console.log(response.data);
                        $scope.persons = response.data;
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