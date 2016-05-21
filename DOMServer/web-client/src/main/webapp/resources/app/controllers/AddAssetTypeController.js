'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('AddAssetTypeController', function ($scope, $http, $uibModalInstance) {

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.ok = function() {
            $http({
                method: 'POST',
                url: HOST + 'assetType',
                data: $scope.assetType
            }).then(function successCallback(response) {
                $uibModalInstance.close(response.data);
            });
        };

    });