(function() {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .directive('jhSortBy', jhSortBy);

    function jhSortBy() {
        var directive = {
            restrict: 'A',
            scope: false,
            require: '^jhSort',
            link: linkFunc
        };

        return directive;

        function linkFunc(scope, element, attrs, parentCtrl) {
            element.bind('click', function () {
                parentCtrl.sort(attrs.jhSortBy);
            });
        }
    }
})();
