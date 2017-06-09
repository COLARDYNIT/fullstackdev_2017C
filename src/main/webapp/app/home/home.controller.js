(function() {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$http'];

    function HomeController ($scope, Principal, LoginService, $state,$http) {
        var vm = this;

        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/availablecars'
        }).then(function successCallback(response) {
            vm.cars = response.data;

        }, function errorCallback(response) {

        });

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });



        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function register () {
            $state.go('register');
        }

    }
})();
