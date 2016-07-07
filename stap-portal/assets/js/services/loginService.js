/**
 * Created by Suny on 7/6/16.
 */
app.service('Session', function () {
    this.create = function (sessionId, user, role, email, avatar) {
        this.sessionId = sessionId;
        this.user = user;
        this.role = role;
        this.email = email;
        this.avatar = avatar;
    };

    this.destroy = function () {
        this.sessionId = null;
        this.user = null;
        this.role = null;
        this.email = null;
        this.avatar = 'app/img/user/02.jpg';
    }

});

app.factory('AuthService', function ($http, Session) {
    var authService = {};

    authService.login = function (credentials) {
        return $http
            .post('/login', credentials)
            .then(function (res) {
                Session.create(res.data.id, res.data.user.user, res.data.user.role, res.data.user.email, res.data.user.avatar);
                return res.data.user;
            });
    };

    authService.isAuthenticated = function () {
        return !!Session.user;
    };

    authService.isAuthorized = function (authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
            authorizedRoles = [authorizedRoles];
        }
        return (authService.isAuthenticated() &&
        authorizedRoles.indexOf(Session.role) !== -1);
    };
    return authService;
})