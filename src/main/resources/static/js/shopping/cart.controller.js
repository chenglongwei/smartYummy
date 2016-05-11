/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', []);


angular.module('smartYummy.app').controller('Cart.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.item = null;
    vm.item_id = null;

    $scope.incQty = function(item_id, quantity) {
        $http.post( '/shopping/update?item_id=' + item_id + '&quantity=' + (quantity + 1) );
        $window.location.href = '/shopping/list';
    }

    $scope.decQty = function(item_id, quantity) {
        if (quantity == 1) {
            $window.alert('Only quantity of current item is 1, can not decrease any more. ' +
                'Please remove the item if you would like to.');
            $window.location.href = '/shopping/list';
        }
        else {
            $http.post( '/shopping/update?item_id=' + item_id + '&quantity=' + (quantity - 1) );
            $window.location.href = '/shopping/list';
        }
    }

    $scope.removeItem = function(item_id) {
        $http.post('/shopping/remove?item_id=' + item_id);
        $window.location.href = '/shopping/list';
    }

});