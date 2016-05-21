'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewZonesController', function ($scope, $http, $uibModal, $location) {

        $scope.zones = [];

        $http({
            method: 'GET',
            url: HOST + 'zone'
        }).then(function successCallback(response) {
            $scope.zones = response.data;
        });

        $scope.viewQRCode = function(assetId) {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/views/view_qrcode.html',
                controller: 'ViewQRCodeController',
                size: "sm",
                resolve: {
                    assetId: function() {
                        return assetId;
                    },
                    type: function() {
                        return "zone";
                    }
                }
            });
        };

    });