/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', ['ui.bootstrap']);


angular.module('smartYummy.app').controller('Adminlist.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.item = null;
    vm.item_id = null;

    $scope.changeTag = function(item_id) {
        //$http.post('/sendcode?email=' + vm.email);

        $http.post('/item/update/tag', item);
    }

    //$scope.register = function(email, pwd1, pwd2, code) {
    //    //$http.post('/sendcode?email=' + vm.email);
    //    //
    //    //$http.post('/register?email=' + email + '&password1=' + pwd1 + '&password2=' + pwd2 + '&code=' + code);
    //    //$window.location.href = '/login';
    //
    //}

});