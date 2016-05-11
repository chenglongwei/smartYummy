/**
 * Created by Lijun on 5/11/2016.
 */

var cartApp = angular.module ("smartYummy.app", []);

cartApp.controller("cartCtrl", function ($scope, $http){

    $scope.addToCart = function (itemId) {
        $http.post('/shopping/add?item_id='+ itemId + '&quantity=1').success(function () {
            alert("Item successfully added to the cart!")
        });
    };
});