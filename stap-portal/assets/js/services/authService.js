/**
 * Created by Suny on 7/6/16.
 */

app.service('AuthService', function ($http) {
    var authService = {};
    var STAP_AUTH_CACHE_KEY = 'stap_local_user_profile_key';
    var authResult = {};

    function loadAuthCache() {
        var userProfileCache = window.localStorage.getItem(STAP_AUTH_CACHE_KEY);
        if(userProfileCache){
            useAuthCache(userProfileCache);
        }
    }

    function writeAuthCache(authResult) {
        var authResultCache = JSON.stringify(authResult)
        window.localStorage.setItem(STAP_AUTH_CACHE_KEY, authResultCache);
        useAuthCache(authResultCache)
    }

    function useAuthCache(authResultCache) {
        authResult = JSON.parse(authResultCache);
        //$http.defaults.headers.common['X-Auth-Token'] = authResult.token;
    }

    function destroyAuthCache() {
        authResult = {};
        window.localStorage.removeItem(STAP_AUTH_CACHE_KEY);
        //$http.defaults.headers.common['X-Auth-Token'] = undefined;
    }

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
                writeAuthCache(res.data);
                return res.data;
            });
    };

    authService.logout = function () {
        destroyAuthCache();
    }

    authService.isAuthenticated = function () {
        return !!authResult.token;
    };

    authService.isAuthorized = function (authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
            authorizedRoles = [authorizedRoles];
        }
        return (authService.isAuthenticated() && authorizedRoles.indexOf(authResult.userProfile.role) !== -1);
    };

    authService.userProfile = function () {
        return authResult.userProfile;
    }

    loadAuthCache();
    return authService;
});

app.run(function ($rootScope, $state, AUTH_EVENTS, AuthService) {
    $rootScope.$on('$stateChangeStart', function (event, next, nextParams, fromState) {
        if('data' in next && 'authorizedRoles' in next.data){
            var authorizedRoles = next.data.authorizedRoles;
            if(!AuthService.isAuthorized(authorizedRoles)){
                event.preventDefault();
                $state.go($state.current, {}, {reload: true});
                $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
            }
        }

        if(!AuthService.isAuthenticated()) {
            if(next.name !== "login.signin"){
                event.preventDefault();
                $state.go('login.signin');
            }
        }
    });
});