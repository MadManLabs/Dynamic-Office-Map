'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewQRCodeController', function ($scope, $http, $uibModalInstance, assetId) {

        $scope.url = HOST + "export/asset/qr/" + assetId;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

    });