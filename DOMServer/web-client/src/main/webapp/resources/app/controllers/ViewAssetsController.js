'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewAssetsController', function ($scope, $http, $uibModal, $routeParams, $location) {

        var floorId = $routeParams.floorId;
        $scope.floor = {};
        $scope.assets = [];

        $http({
            method: 'GET',
            url: HOST + 'floor/' + floorId
        }).then(function successCallback(response) {
            $scope.floor = response.data;
        });

    });