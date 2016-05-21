'use strict';

angular
    .module('dynamicOfficeMapApp', [
        'ngRoute',
        'ui.bootstrap'
    ])

    .config(function($routeProvider) {
        $routeProvider.
        when('/', {
            templateUrl: 'app/views/view_floors.html',
            controller: 'ViewFloorsController'
        }).
        when('/floor/:floorId', {
            templateUrl: 'app/views/update_floor.html',
            controller: 'UpdateFloorController'
        }).
        when('/view/:floorId', {
            templateUrl: 'app/views/view_floor.html',
            controller: 'ViewFloorController'
        }).
        when('/assets/:floorId', {
            templateUrl: 'app/views/view_assets.html',
            controller: 'ViewAssetsController'
        }).
        when('/person/:personId', {
            templateUrl: 'app/views/view_person.html',
            controller: 'ViewPersonController'
        }).
        when('/personMap/:personId', {
            templateUrl:  '../app/views/view_person_map.html',
            controller: 'ViewPersonMapController'
        }).
        otherwise({
            redirectTo: '/'
        });
    });