'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewPersonMapController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var personUsername = $routeParams.username;
        $scope.person = {};

        var canvas = new fabric.CanvasWithViewport('floorMap', { selection: false });
        canvas.isGrabMode = true;

        $http({
            method: 'GET',
            url: HOST + 'person/username/' + personUsername
        }).then(function successCallback(response) {
            $scope.person = response.data;
            var floorId;

            var temporaryZone = false;
            var permanentZone = false;

            if (response.data.temporaryFloorId) {
                floorId = response.data.temporaryFloorId;
                temporaryZone = true;
            }

            if (response.data.permanentFloorId) {
                floorId = response.data.permanentFloorId;
                permanentZone = true;
            }

            if (floorId) {
                $http({
                    method: 'GET',
                    url: HOST + 'floor/' + floorId
                }).then(function successCallback(response) {
                    var floor = response.data;

                    String.prototype.replaceAll = function(search, replacement) {
                        var target = this;
                        return target.replace(new RegExp(search, 'g'), replacement);
                    };

                    if (floor.map) {

                        floor.map = floor.map.replaceAll('localhost', window.location.hostname);
                        floor.map = floor.map.replace( /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/gi, window.location.hostname);

                        canvas.loadFromJSON(floor.map, canvas.renderAll.bind(canvas), function(o, object) {
                            object.hasControls = false;
                            object.hasRotatingPoint = false;
                            object.lockMovementX = true;
                            object.lockMovementY = true;
                            object.selectable = false;

                            if (temporaryZone) {
                                if (object.objectType && object.objectType === 'Zone') {
                                    if (object.idObject == $scope.person.temporaryZoneId) {
                                        object.stroke = 'blue';
                                        object.strokeWidth = 5;
                                    }
                                }
                            }

                            if (permanentZone) {
                                if (object.objectType && object.objectType === 'Asset') {
                                    if (object.idObject == $scope.person.permanentDeskId) {
                                        object.stroke = 'red';
                                        object.strokeWidth = 5;
                                    }
                                }
                            }

                        });
                    }
                });
            }

        });

    });