/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', ['ui.bootstrap']);


angular.module('smartYummy.app').controller('Order.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.item = null;
    vm.item_id = null;

    $scope.cancelOrder = function(order_id) {
        //$http.post('/sendcode?email=' + vm.email);

        $http.post('/order/remove?id=' + order_id);
    }

});