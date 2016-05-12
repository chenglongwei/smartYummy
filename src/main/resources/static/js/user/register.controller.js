/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', ['ui.bootstrap']);


angular.module('smartYummy.app').controller('Register.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.email = null;
    vm.password1 = null;
    vm.password2 = null;
    vm.code = null;

    $scope.sendCode = function(email) {
        //$http.post('/sendcode?email=' + vm.email);

        //$http.post('/sendcode?email=' + email).then(handleSuccess, handleError);
        $http.post('/sendcode?email=' + email);
    }

    $scope.register = function(email, pwd1, pwd2, code) {
        //$http.post('/sendcode?email=' + vm.email);

        $http.post('/register?email=' + email + '&password1=' + pwd1 + '&password2=' + pwd2 + '&code=' + code);
        $window.location.href = '/login';

    }

    function handleSuccess(res) {
        if (res.data.status=='success') {
            $window.alert('Order Cancelled successfully!');
            $window.location.href = '/order/list';
        }
        else {
            //$window.alert('Sorry, the order can not be fullfilled in your required time. Our earliest available );
            $window.alert('Order can not be cancelled because it is already in process!');

        }
        //$window.alert('Order Cancelled successfully!');
        //$window.location.href = '/order/list';
    }

    function handleError(res) {
        $window.alert('Order can not be cancelled because it is already in process!');
        //$window.location.href = '/order/list';
    }

});