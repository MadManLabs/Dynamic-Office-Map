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
            controller: 'FloorsController'
        }).
        when('/floor/:floorId', {
            templateUrl: 'app/views/update_floor.html',
            controller: 'UpdateFloorController'
        }).
        when('/view/:floorId', {
            templateUrl: 'app/views/view_floor2.html',
            controller: 'ViewFloorController'
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