'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewFloorController', function ($scope, $http, $uibModal, $routeParams, $location) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};

        var canvas = new fabric.CanvasWithViewport('floorMap', { selection: false });
        canvas.isGrabMode = true;

        $http({
            method: 'GET',
            url: HOST + 'floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;

            if ($scope.floor.map) {
                canvas.loadFromJSON($scope.floor.map, canvas.renderAll.bind(canvas), function(o, object) {

                });
            }
        });

    });