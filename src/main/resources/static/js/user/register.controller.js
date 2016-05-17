/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', []);


angular.module('smartYummy.app').controller('Register.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.email = null;
    vm.password1 = null;
    vm.password2 = null;
    vm.code = null;

    $scope.sendCode = function(email) {
        $http.post('/sendcode?email=' + email).then(handleVeriCodeSuccess, handleVeriCodeError);
    }

    $scope.register = function(email, pwd1, pwd2, code) {
        $http.post('/register?email=' + email + '&password1=' + pwd1 + '&password2=' + pwd2 + '&code=' + code)
            .then(handleRegisterSuccess, handleRegisterError);
    }

    function handleVeriCodeSuccess(res) {
        $window.alert('Verification Code is sent successfully! Please check in your email.');
    }

    function handleVeriCodeError(res) {
        $window.alert('Send Verification Code failed! Please try it again.');
    }

    function handleRegisterSuccess(res) {
        if (res.data.status=='success') {
            $window.alert('Register Account Successfully!');
            $window.location.href = '/login';
        } else {
            $window.alert(res.data.error);

        }
    }

    function handleRegisterError(res) {
        $window.alert('Register Account Failed!');
    }

});