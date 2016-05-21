'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('ViewQRCodeController', function ($scope, $http, $uibModalInstance, assetId, type) {

        $scope.url = HOST + "export/" + type + "/qr/" + assetId;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

    });