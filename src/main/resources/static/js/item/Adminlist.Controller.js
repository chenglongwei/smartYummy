/**
 * Created by StarRUC on 5/10/16.
 */

/**
 * Created by StarRUC on 5/10/16.
 */
var app = angular.module('smartYummy.app', []);


angular.module('smartYummy.app').controller('Adminlist.Controller', function ($scope, $log, $http, $timeout, $attrs, $window) {

    var vm = this;
    vm.item = null;
    vm.item_id = null;

    $scope.changeTag = function(item_id) {
        //$http.post('/sendcode?email=' + vm.email);

        $http.post('/item/update/tag?id=' + item_id).then(handleSuccess, handleError);
    }

    function handleSuccess(res) {
        $window.location.href = '/item/adminlist';
    }

    function handleError(res) {
        $window.alert('Order can not be cancelled because it is already in process!');
        //$window.location.href = '/order/list';
    }

});