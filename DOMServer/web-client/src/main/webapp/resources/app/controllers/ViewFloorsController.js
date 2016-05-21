'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewFloorsController', function ($scope, $http, $uibModal, $location) {

        $scope.floors = [];

        $scope.goToFloor = function(floorId) {
            $location.path('/floor/' + floorId);
        };

        $scope.viewFloor = function(floorId) {
            $location.path('/view/' + floorId);
        };

        $scope.viewAssets = function (floorId) {
            $location.path('/assets/' + floorId);
        };

        $scope.addFloor = function() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/add_floor.html',
                controller: 'AddFloorController'
            });

            modalInstance.result.then(function (floor) {
                $scope.floors.push(floor);
                $location.path('/floor/' + floor.id);
            });
        };

        $http({
            method: 'GET',
            url: HOST + 'floor'
        }).then(function successCallback(response) {
            $scope.floors = response.data;
        });

    });