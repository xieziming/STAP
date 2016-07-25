app.service('NotificationService', function ($rootScope) {
    var notificationService = {};

    notificationService.error = function (msg) {
        this.broadcast('error', msg);
    };

    notificationService.broadcast = function (type, msg) {
        $rootScope.$broadcast('newNotification', {'type': type, 'msg': msg});
    };

    return notificationService;
});