'use strict';

angular
    .module('dynamicOfficeMapApp', [
        'ngRoute',
        'ui.bootstrap'
    ])

    .config(function($routeProvider) {
        $routeProvider.
        when('/', {
            templateUrl: HOST + '/app/views/floors.html',
            controller: 'FloorsController'
        }).
        when('/floor/:floorId', {
            templateUrl: HOST + '/app/views/update_floor.html',
            controller: 'FloorController'
        }).
        when('/view/:floorId', {
            templateUrl: HOST + '/app/views/view_floor.html',
            controller: 'ViewFloorController'
        }).
        when('/person/:personId', {
            templateUrl: HOST + '/app/views/view_person.html',
            controller: 'ViewPersonController'
        }).
        when('/personMap/:personId', {
            templateUrl: HOST + '/app/views/view_person_map.html',
            controller: 'ViewPersonMapController'
        }).
        otherwise({
            redirectTo: '/'
        });
    });