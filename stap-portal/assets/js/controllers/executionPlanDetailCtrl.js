'use strict';
/** 
  * controller for Execution Plan Detail
*/
app.controller('executionPlanDetailCtrl', ["$scope", "$filter", "$http", "ngTableParams", "ENV_CONFIG", "$stateParams", function ($scope, $filter, $http, ngTableParams, ENV_CONFIG, $stateParams) {


    $http.get(ENV_CONFIG.gatewayUrl + '/execution_plan/' + $stateParams.id).then(function (res) {
        $scope.executionPlan = {
            id: res.data.id,
            name: res.data.name,
            description: res.data.description,
            status: res.data.status
        };
        $scope.metaDataList = res.data.executionPlanMetaDtoList;
        $scope.executionPlanLoglist = res.data.executionLogDtoList;
        // $scope.tableParams = new ngTableParams({
        //     page: 1, // show first page
        //     count: 5, // count per page
        //     sorting: {
        //         id: 'asc' // initial sorting
        //     }
        // }, {
        //     total: executionPlanData.length, // length of data
        //     getData: function ($defer, params) {
        //         // use build-in angular filter
        //         var orderedData = params.filter() ? $filter('filter')(executionPlanData, params.filter()) : executionPlanData;
        //         orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
        //         $scope.execution_plans = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
        //         params.total(orderedData.length);
        //
        //         // set total for recalc pagination
        //         $defer.resolve($scope.execution_plans);
        //     }
        // });
        $scope.removeImage = function () {
            $scope.noImage = true;
        };
        $scope.obj = new Flow();

        $scope.userInfo = {
            firstName: 'Peter',
            lastName: 'Clark',
            url: 'www.example.com',
            email: 'peter@example.com',
            phone: '(641)-734-4763',
            gender: 'male',
            zipCode: '12345',
            city: 'London (UK)',
            avatar: 'assets/images/avatar-1-xl.jpg',
            twitter: '',
            github: '',
            facebook: '',
            linkedin: '',
            google: '',
            skype: 'peterclark82'
        };
        if ($scope.userInfo.avatar == '') {
            $scope.noImage = true;
        }
    });

    $http.get(ENV_CONFIG.gatewayUrl + '/execution_plan/' + $stateParams.id+"/execution_list").then(function (res) {
        var executionBriefData = res.data;
        $scope.tableParams = new ngTableParams({
            page: 1, // show first page
            count: 5, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: executionBriefData.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(executionBriefData, params.filter()) : executionBriefData;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                $scope.execution_briefs = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve($scope.execution_briefs);
            }
        });
    });
}]);