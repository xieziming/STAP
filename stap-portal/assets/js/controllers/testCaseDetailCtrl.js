'use strict';
/** 
  * controller for Test Case Detail
*/
app.controller('testCaseDetailCtrl', ["$scope", "$filter", "$http", "ngTableParams", "ENV_CONFIG", "$stateParams", function ($scope, $filter, $http, ngTableParams, ENV_CONFIG, $stateParams) {


    $http.get(ENV_CONFIG.gatewayUrl + '/test_case/' + $stateParams.id).then(function (res) {
        $scope.testCase = res.data.testCase;

        var testDataList = res.data.testDataDtoList;
        var testStepList = res.data.testStepDtoList;
        var testCaseMetaList = res.data.testCaseMetaDtoList;

        $scope.testDataTableParams = new ngTableParams({
            page: 1, // show first page
            count: 15, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: testDataList.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(testDataList, params.filter()) : testDataList;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.testDataList = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.testDataList);
            }
        });

        $scope.testStepTableParams = new ngTableParams({
            page: 1, // show first page
            count: 15, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: testStepList.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(testStepList, params.filter()) : testStepList;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.testStepList = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.testStepList);
            }
        });

        $scope.testCaseMetaTableParams = new ngTableParams({
            page: 1, // show first page
            count: 15, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: testStepList.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(testCaseMetaList, params.filter()) : testCaseMetaList;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.testCaseMetaList = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.testCaseMetaList);
            }
        });
    });
}]);