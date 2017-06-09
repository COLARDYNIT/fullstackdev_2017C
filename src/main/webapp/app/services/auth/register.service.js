(function () {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
