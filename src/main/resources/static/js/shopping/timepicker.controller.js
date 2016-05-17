/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', []);


angular.module('smartYummy.app').controller('Timepicker.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    $scope.validateTime = function() {
        var timeStr = $("#datetimepicker1").data('date');
        var time = new Date(timeStr);
        var yy = time.getFullYear();
        var mm = time.getMonth() + 1;
        var dd = time.getDate();
        var hh = time.getHours();
        var min = time.getMinutes();

        $http.post( '/shopping/save?year=' + yy
            + '&month=' + mm
            + '&day=' + dd
            + '&hour=' + hh
            + '&minute=' + min
        ).then(handleSuccess, handleError);


    }

    function handleSuccess(res) {
        if (res.data.status=='success') {
            $window.alert('Order Confirmed.');
            $window.location.href='/';
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