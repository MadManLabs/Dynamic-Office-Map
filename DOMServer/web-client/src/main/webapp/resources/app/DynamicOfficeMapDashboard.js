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
        when('/zones', {
            templateUrl: 'app/views/view_zones.html',
            controller: 'ViewZonesController'
        }).
        when('/floor/:floorId', {
            templateUrl: 'app/views/update_floor.html',
            controller: 'UpdateFloorController'
        }).
        when('/persons/:floorId', {
            templateUrl: 'app/views/view_persons.html',
            controller: 'ViewPersonsController'
        }).
        when('/view/:floorId', {
            templateUrl: 'app/views/view_floor.html',
            controller: 'ViewFloorController'
        }).
        when('/assets/:floorId', {
            templateUrl: 'app/views/view_assets.html',
            controller: 'ViewAssetsController'
        }).
        when('/assets/:floorId/:assetTypeId', {
            templateUrl: 'app/views/view_assets.html',
            controller: 'ViewAssetsController'
        }).
        when('/person/:personId', {
            templateUrl: 'app/views/view_person.html',
            controller: 'ViewPersonController'
        }).
        when('/personMap/:username', {
            templateUrl:  '../app/views/view_person_map.html',
            controller: 'ViewPersonMapController'
        }).
        when('/personMap/:username/:refresh', {
            templateUrl:  '../app/views/view_person_map.html',
            controller: 'ViewPersonMapController'
        }).
        when('/assetMap/:assetId', {
            templateUrl:  '../app/views/view_asset_map.html',
            controller: 'ViewAssetMapController'
        }).
        otherwise({
            redirectTo: '/'
        });
    });