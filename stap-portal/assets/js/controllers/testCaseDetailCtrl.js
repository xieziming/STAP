'use strict';
/** 
  * controller for Test Case Detail
*/
app.controller('testCaseDetailCtrl', function ($scope, StapTableService, $http, ENV_CONFIG, $stateParams) {


    $http.get(ENV_CONFIG.gatewayUrl + '/test_case/' + $stateParams.id).then(function (res) {
        $scope.testCase = res.data.testCase;

        var testDataList = res.data.testDataDtoList;
        var testStepList = res.data.testStepDtoList;
        var testCaseMetaList = res.data.testCaseMetaDtoList;
        var executionBriefList = res.data.executionBriefDtoList;

        $scope.testDataTable = StapTableService.createStapTable(testDataList);
        $scope.testStepTable = StapTableService.createStapTable(testStepList);
        $scope.testCaseMetaTable = StapTableService.createStapTable(testCaseMetaList);
        $scope.executionTable = StapTableService.createStapTable(executionBriefList);
    });
});