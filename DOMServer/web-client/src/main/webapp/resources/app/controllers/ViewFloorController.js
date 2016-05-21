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
            url: HOST + 'floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            if ($scope.floor.map) {
                canvas.loadFromJSON($scope.floor.map, canvas.renderAll.bind(canvas), function(o, object) {
                    object.hasControls = false;
                    object.hasRotatingPoint = false;
                    object.lockMovementX = true;
                    object.lockMovementY = true;
                });
            }

            canvas.on('object:selected', function(object) {
                if (object.target.objectType && object.target.objectType === 'Asset') {
                    $scope.$apply(function () {
                        $scope.persons = [];
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = object.target;
                    });
                    $http({
                        method: 'GET',
                        url: HOST + 'person/desk/' + object.target.idObject
                    }).then(function successCallback(response) {
                        if (response.data) {
                            $scope.persons.push(response.data);
                        }
                    });
                } else if (object.target.objectType && object.target.objectType === 'Zone') {
                    $scope.$apply(function() {
                        $scope.persons = [];
                        $scope.objectSelected = true;
                        $scope.objectSelectedInfo = object.target;
                    });
                    $http({
                        method: 'GET',
                        url: HOST + '/person/zone/' + object.target.idObject
                    }).then(function successCallback(response) {
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