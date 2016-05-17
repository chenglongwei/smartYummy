/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', ['ngFileUpload']);


angular.module('smartYummy.app').controller('CreateItem.Controller',
    ['$scope', 'Upload', '$timeout', function ($scope, Upload, $log, $http, $timeout) {
        vm = this;
        vm.picFile = null;
        vm.name = null;

    $scope.uploadPic = function(file, name) {
        file.upload = Upload.upload({
            url: '/item/upload',
            data: {name: name, file: file}
        });

        file.upload.then(function (response) {
            $timeout(function () {
                file.result = response.data;
            });
        }, function (response) {
            if (response.status > 0)
                $scope.errorMsg = response.status + ': ' + response.data;
        }, function (evt) {
            // Math.min is to fix IE which reports 200% sometimes
            file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
        });
    }
}]);