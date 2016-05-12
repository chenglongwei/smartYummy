/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', []);


angular.module('smartYummy.app').controller('Order.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.item = null;
    vm.item_id = null;

    $scope.cancelOrder = function(order_id) {
        //$http.post('/sendcode?email=' + vm.email);

        $http.post('/order/remove?id=' + order_id).then(handleSuccess, handleError);
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