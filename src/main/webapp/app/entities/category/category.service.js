(function() {
    'use strict';
    angular
        .module('fullstackdev2017CApp')
        .factory('Category', Category);

    Category.$inject = ['$resource'];

    function Category ($resource) {
        var resourceUrl =  'api/categories/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();