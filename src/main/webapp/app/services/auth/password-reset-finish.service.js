(function() {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .factory('PasswordResetFinish', PasswordResetFinish);

    PasswordResetFinish.$inject = ['$resource'];

    function PasswordResetFinish($resource) {
        var service = $resource('api/account/reset_password/finish', {}, {});

        return service;
    }
})();
