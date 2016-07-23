'use strict';
/** 
  * controller for Execution Plan Detail
*/
app.controller('executionDetailCtrl', ["$scope", "$filter", "$http", "ngTableParams", "ENV_CONFIG", "$stateParams", function ($scope, $filter, $http, ngTableParams, ENV_CONFIG, $stateParams) {


    $http.get(ENV_CONFIG.gatewayUrl + '/execution/' + $stateParams.id).then(function (res) {
        $scope.execution = res.data;
        $scope.executionPlan = res.data.executionPlanDto;
        $scope.executionContext = res.data.executionContext;
        $scope.testCase = res.data.testCaseDto.testCase;
        $scope.executionLogs = res.data.executionLogDtoList;
        $scope.metaDataList = res.data.executionPlanMetaDtoList;
        $scope.executionSteps = res.data.executionStepDtoList;

        $scope.executionPlanLoglist = res.data.executionLogDtoList;
        var executionOutputTextData = res.data.executionOutputTextDtoList;
        var executionOutputFileData = res.data.executionOutputFileDtoList;
        var executionLogData = res.data.executionLogDtoList;

        $scope.outputTextTableParams = new ngTableParams({
            page: 1, // show first page
            count: 15, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: executionOutputTextData.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(executionOutputTextData, params.filter()) : executionOutputTextData;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.executionOutputTexts = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.executionOutputTexts);
            }
        });

        $scope.outputFileTableParams = new ngTableParams({
            page: 1, // show first page
            count: 15, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: executionOutputFileData.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(executionOutputFileData, params.filter()) : executionOutputFileData;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.executionOutputFiles = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.executionOutputFiles);
            }
        });

        $scope.executionLogTableParams = new ngTableParams({
            page: 1, // show first page
            count: 50, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: executionLogData.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(executionLogData, params.filter()) : executionLogData;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.executionLogs = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.executionLogs);
            }
        });
    });
}]);