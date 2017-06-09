(function() {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .controller('RentalDetailController', RentalDetailController);

    RentalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Rental', 'Car', 'Customer'];

    function RentalDetailController($scope, $rootScope, $stateParams, previousState, entity, Rental, Car, Customer) {
        var vm = this;

        vm.rental = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fullstackdev2017CApp:rentalUpdate', function(event, result) {
            vm.rental = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
