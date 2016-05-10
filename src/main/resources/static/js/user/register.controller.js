/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', ['ui.bootstrap']);


angular.module('smartYummy.app').controller('Register.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    $scope.sendCode = function(email) {
        $http.post('/sendcode?email=' + email);
    }


});