function createStapTable(data, ngTableParams, $filter){
    return new ngTableParams(
        {
            page: 1, // show first page
            count: 15, // count per page
            sorting: {
                id: 'asc' // initial sorting
            }
        }, {
            total: data.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filter
                var orderedData = params.filter() ? $filter('filter')(data, params.filter()) : data;
                orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : orderedData;
                var filteredData = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                params.total(orderedData.length);

                // set total for recalc pagination
                $defer.resolve(filteredData);
            }
        });
}