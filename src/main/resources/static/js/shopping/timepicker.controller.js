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

    $scope.confirmTime = function() {
        if (!vm.yy || !vm.mm || !vm.dd || !vm.hh || !vm.min ) {
            $window.alert('Incomplete input!');
            return;
        }

        if (vm.hh < 6 || vm.hh >= 9) {
            $window.alert('Invalid input! Our operation time is 6:00am - 9:00pm, the latest pickup time is 8:59pm');
            return;
        }

        vm.today = new Date();
        vm.intendDate = new Date(vm.mm + '/' + vm.dd + '/' + vm.yy);

        var miliseconds = vm.intendDate - vm.today;
        var seconds = miliseconds/1000;
        var minutes = seconds/60;
        var hours = minutes/60;
        var days = hours/24;

        if (miliseconds < 0 || days > 29) {
            $window.alert('Invalid input! You can only select a date within 30 days including today');
            return;
        }

        $http.post( '/shopping/save?year=' + vm.yy
            + '&month=' + vm.mm
            + '&day=' + vm.dd
            + '&hour=' + vm.hh
            + '&minute=' + vm.min
        ).then({


        });
        $window.location.href = '/shopping/picktime';
    }


});