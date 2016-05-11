/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', []);


angular.module('smartYummy.app').controller('Timepicker.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.today = null;
    vm.intendDate = null;
    vm.yy = null;
    vm.mm = null;
    vm.dd = null;
    vm.hh = null;
    vm.min = null;

    //$scope.today = null;
    //$scope.intendDate = null;
    //$scope.yy = null;
    //$scope.mm = null;
    //$scope.dd = null;
    //$scope.hh = null;
    //$scope.min = null;

    $scope.confirmTime = function(yy, mm, dd, hh, min) {
        //if (!vm.yy || !vm.mm || !vm.dd || !vm.hh || !vm.min ) {
    //$scope.confirmTime = function() {
    //    if (!$scope.yy || !$scope.mm || !$scope.dd || !$scope.hh || !$scope.min ) {
        if (!yy || !mm || !dd || !hh || !min ) {
            $window.alert('Incomplete input!');
            return;
        }

        if (hh < 6 || hh >= 21) {
            $window.alert('Invalid input! Our operation time is 6:00 - 21:00, the latest pickup time is 20:59.');
            return;
        }

        //var tmpToday = new Date();
        //vm.today = new Date(tmpToday.getYear(), tmpToday.getMonth() + 1, tmpToday.getHours(), tmpToday.getMinutes(), 0);
        //vm.intendDate = new Date(mm + '/' + dd + '/' + yy);
        vm.today = new Date();
        vm.intendDate = new Date(yy, (mm -1), dd, hh, min, 0);

        var miliseconds = vm.intendDate - vm.today;
        var seconds = miliseconds/1000;
        var minutes = seconds/60;
        var hours = minutes/60;
        var days = hours/24;

        if (miliseconds < 0 || days > 29) {
            $window.alert('Invalid input! You can only select a date within 30 days including today');
            return;
        }

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
        return $q.reject(res.data);
    }



});