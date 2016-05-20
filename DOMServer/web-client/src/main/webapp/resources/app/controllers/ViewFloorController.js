'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewFloorController', function ($scope, $http, $uibModal, $routeParams, $location) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};

        $scope.objectSelected = false;
        $scope.objectSelectedInfo = {};

        $scope.persons = [];

        var grid = 50;
        var gridSize = 1100;

        var canvas = new fabric.Canvas('floorMap', { selection: false });

        $scope.viewPerson = function(personId) {
            $location.path('/person/' + personId);
        };

        $http({
            method: 'GET',
            url: HOST + '/dynamaps/api/v1/office/floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            if ($scope.floor.map) {
                canvas.loadFromJSON($scope.floor.map, canvas.renderAll.bind(canvas), function(o, object) {
                    object.hasControls = false;
                    object.hasRotatingPoint = false;
                    object.lockMovementX = true;
                    object.lockMovementY = true;
                });
            } else {
                for (var i = 0; i < (gridSize / grid); i++) {
                    canvas.add(new fabric.Line([ i * grid, 0, i * grid, gridSize], { stroke: '#ccc', selectable: false }));
                    canvas.add(new fabric.Line([ 0, i * grid, gridSize, i * grid], { stroke: '#ccc', selectable: false }))
                }
            }

            canvas.on('object:selected', function(object) {
                if (object.target.objectType && object.target.objectType === 'Desk') {
                    $scope.$apply(function () {
                        $scope.persons = [];
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = object.target;
                    });
                    $http({
                        method: 'GET',
                        url: HOST + '/dynamaps/api/v1/office/person/' + object.target.idObject + '/desk'
                    }).then(function successCallback(response) {
                        if (response.data) {
                            $scope.persons.push(response.data);
                        }
                    });
                } else if (object.target.objectType && object.target.objectType === 'Zone') {
                    console.log('selected zone');
                    $scope.$apply(function() {
                        $scope.persons = [];
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