'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewFloorController', function ($scope, $http, $uibModal, $routeParams, $location) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};

        $scope.objectSelected = false;
        $scope.objectSelectedInfo = {};

        $scope.persons = [];
        $scope.title = "";

        var grid = 50;
        var gridSize = 1100;

        var canvas = new fabric.CanvasWithViewport('floorMap', { selection: false });

        $scope.viewPerson = function(personId) {
            $location.path('/person/' + personId);
        };

        $http({
            method: 'GET',
            url: HOST + 'floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            if ($scope.floor.map) {

                $scope.floor.map = $scope.floor.map.replaceAll('localhost', window.location.hostname);
                $scope.floor.map = $scope.floor.map.replace( /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/gi, window.location.hostname);

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
                            $scope.title = "Desk Information";
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
                        url: HOST + 'zone/' + object.target.idObject
                    }).then(function successCallback(response) {
                        $scope.title = response.data.name + " Zone";
                        if (response.data.parentName) {
                            $scope.title += " (" + response.data.parentName + ")";
                        }
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

        $scope.grabMode = function(grabMode) {
            canvas.isGrabMode = grabMode;
        };

    });