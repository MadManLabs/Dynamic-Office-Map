'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewPersonsController', function ($scope, $http, $routeParams, $location) {

        var floorId = $routeParams.floorId;
        $scope.persons = [];

        $http({
            method: 'GET',
            url: HOST + 'person/map/' + floorId
        }).then(function successCallback(response) {
            $scope.persons = response.data;
        });

    });