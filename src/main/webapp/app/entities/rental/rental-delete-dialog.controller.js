(function() {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .controller('RentalDeleteController',RentalDeleteController);

    RentalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Rental'];

    function RentalDeleteController($uibModalInstance, entity, Rental) {
        var vm = this;

        vm.rental = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Rental.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
