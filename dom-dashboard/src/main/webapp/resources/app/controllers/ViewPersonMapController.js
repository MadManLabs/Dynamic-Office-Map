'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewPersonMapController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var personId = $routeParams.personId;
        $scope.person = {};

        var canvas = new fabric.Canvas('floorMap', { selection: false });

        $http({
            method: 'GET',
            url: HOST + '/dynamaps/api/v1/office/person/byid/' + personId
        }).then(function successCallback(response) {
            $scope.person = response.data;
            var floor = $scope.person.desk.zone.floor;

            if (floor.map) {
                canvas.loadFromJSON(floor.map, canvas.renderAll.bind(canvas), function(o, object) {
                    object.hasControls = false;
                    object.hasRotatingPoint = false;
                    object.lockMovementX = true;
                    object.lockMovementY = true;
                    object.selectable = false;

                    if (object.objectType && object.objectType === 'Desk') {
                        if (object.idObject == $scope.person.desk.id) {
                            object.stroke = 'red';
                            object.strokeWidth = 2;
                        }
                    }
                });
            }

        });

    });