'use strict';
/**
 * controllers for execution plan page
 * with simple table with sorting and filtering on AngularJS
 */
app.controller('executionPlanCtrl', ["$scope", "$filter", "$http", "ngTableParams", "ENV_CONFIG", function ($scope, $filter, $http, ngTableParams, ENV_CONFIG) {
    $http.get(ENV_CONFIG.gatewayUrl + '/execution_plan').then(function (res) {
        var executionPlanData = res.data;
        $scope.tableParams = new ngTableParams({
            page: 1, // show first page
            count: 5, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: executionPlanData.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(executionPlanData, params.filter()) : executionPlanData;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.execution_plans = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.execution_plans);
            }
        });
    });
}]);
