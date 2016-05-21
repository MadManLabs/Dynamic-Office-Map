'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewAssetMapController', function ($scope, $http, $uibModal, $routeParams, NotificationService) {

        var assetId = $routeParams.assetId;
        $scope.asset = {};
        $scope.floor = {};

        var canvas = new fabric.CanvasWithViewport('floorMap', { selection: false });
        canvas.isGrabMode = true;

        $http({
            method: 'GET',
            url: HOST + 'asset/' + assetId
        }).then(function successCallback(response) {
            $scope.asset = response.data;

            var floorId = response.data.floorId;
            if (floorId) {
                $http({
                    method: 'GET',
                    url: HOST + 'floor/' + floorId
                }).then(function successCallback(response) {
                    var floor = response.data;

                    var id = null;
                    if ($scope.asset.onMap) {
                        id = $scope.asset.id;
                    } else if ($scope.asset.closeByAssetId) {
                        id = $scope.asset.closeByAssetId;
                    } else {
                        // alert("This asset is not added on the map!");
                        // $("#floorMap").remove();
                        $scope.error = "This asset is not added on the map!";
                        return;
                    }

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

                            if (object.objectType && object.objectType === 'Asset') {
                                if (object.idObject == id) {
                                    object.stroke = 'red';
                                    object.strokeWidth = 5;
                                }
                            }

                        });
                    }
                });
            }

        });

    });