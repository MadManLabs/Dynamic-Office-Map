'use strict';

angular.module('dynamicOfficeMapApp')
    .controller('AddFloorController', function ($scope, $http, $uibModalInstance) {

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.ok = function() {
            $http({
                method: 'POST',
                url: HOST + 'floor',
                data: $scope.floor
            }).then(function successCallback(response) {
                $uibModalInstance.close(response.data);
            });
        };

    });