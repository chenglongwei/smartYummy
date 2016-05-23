/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', []);


angular.module('smartYummy.app').controller('Reset.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    $scope.reset = function() {
        //$window.location.href='/admin/report/popularity?from=' + startTime + '&to=' + endTime + '&category=' + category;
        $http.post('/admin/reset').then(handleSuccess, handleError);
    }

    function handleSuccess(res) {
        if (res.data.status=='success') {
            $window.alert('Reset Finished! All orders with status "Not Started" has been deleted.');
            //$window.location.href='/';
        }
        else {
            //$window.alert('Sorry, the order can not be fullfilled in your required time. Our earliest available );
            $window.alert(res.data.error);
        }
    }

    function handleError(res) {
        $window.alert(res.data);
    }



});