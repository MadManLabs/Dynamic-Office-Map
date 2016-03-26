'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('FloorsController', function ($scope, $http, $uibModal, $location) {

        $scope.floors = [];

        $scope.toToFloor = function(floorId) {
            $location.path('/floor/' + floorId);
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
            url: HOST + '/dynamaps/api/v1/office/floor'
        }).then(function successCallback(response) {
            $scope.floors = response.data;
        });

    });