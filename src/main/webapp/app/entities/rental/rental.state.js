(function() {
    'use strict';

    angular
        .module('fullstackdev2017CApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rental', {
            parent: 'entity',
            url: '/rental?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rentals'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rental/rentals.html',
                    controller: 'RentalController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('rental-detail', {
            parent: 'rental',
            url: '/rental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rental'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rental/rental-detail.html',
                    controller: 'RentalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Rental', function($stateParams, Rental) {
                    return Rental.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rental-detail.edit', {
            parent: 'rental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental/rental-dialog.html',
                    controller: 'RentalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Rental', function(Rental) {
                            return Rental.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rental.new', {
            parent: 'rental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental/rental-dialog.html',
                    controller: 'RentalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                startDate: null,
                                endDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rental', null, { reload: 'rental' });
                }, function() {
                    $state.go('rental');
                });
            }]
        })
        .state('rental.edit', {
            parent: 'rental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental/rental-dialog.html',
                    controller: 'RentalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Rental', function(Rental) {
                            return Rental.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rental', null, { reload: 'rental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rental.delete', {
            parent: 'rental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental/rental-delete-dialog.html',
                    controller: 'RentalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Rental', function(Rental) {
                            return Rental.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rental', null, { reload: 'rental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
