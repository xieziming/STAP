/**
 * Created by Suny on 7/6/16.
 */
app.service('Session', function () {
    this.create = function (userProfile) {
        this.sessionId = userProfile.token;
        this.user = userProfile.name;
        this.role = userProfile.role;
        this.email = userProfile.email;
        this.avatar = 'app/img/user/02.jpg';//userProfile.avatar;
    };

    this.destroy = function () {
        this.sessionId = null;
        this.user = null;
        this.role = null;
        this.email = null;
        this.avatar = 'app/img/user/02.jpg';
    };
    return this;
});

app.factory('AuthService', function ($http, Session) {
    var authService = {};

    authService.login = function (credentials) {
        return $http
            ({ method: 'POST',
                url: 'http://stap.xieziming.com:8080/stap-gateway/authorize',
                data: credentials,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                }})
            .then(function (res) {
                if (res.status == 200 ){
                    return res.data;
                }else{
                    return null;
                }
            });
    };

    authService.isAuthenticated = function () {
        return !!Session.user;
    };

    authService.isAuthorized = function (authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
            authorizedRoles = [authorizedRoles];
        }
        return (authService.isAuthenticated() && authorizedRoles.indexOf(Session.role) !== -1);
    };
    return authService;
});

app.run(function ($rootScope, AUTH_EVENTS, AuthService, $state) {
    $rootScope.$on('$stateChangeStart', function (event, next) {
        if(!AuthService.isAuthenticated()) {
            $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
        }
    });
    $rootScope.$on(AUTH_EVENTS.notAuthenticated, function (){ $state.go('login.signin');});
});