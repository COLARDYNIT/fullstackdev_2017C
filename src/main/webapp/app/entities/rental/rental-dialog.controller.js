(function() {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .controller('RentalDialogController', RentalDialogController);

    RentalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Rental', 'Car', 'Customer'];

    function RentalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Rental, Car, Customer) {
        var vm = this;

        vm.rental = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.cars = Car.query();
        vm.customers = Customer.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.rental.id !== null) {
                Rental.update(vm.rental, onSaveSuccess, onSaveError);
            } else {
                Rental.save(vm.rental, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fullstackdev2017CApp:rentalUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.startDate = false;
        vm.datePickerOpenStatus.endDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
