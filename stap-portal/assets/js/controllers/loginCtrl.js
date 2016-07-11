'use strict';
/** 
  * controller for User Profile Example
*/
app.controller('LoginCtrl', function ($scope, $rootScope, AUTH_EVENTS, AuthService, Session, $state) {
    $scope.credentials = {
        principal : '',
        password : ''
    };
    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (authResult) {
            if(authResult.authSuccess) {
                Session.create(authResult.userProfile);
                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                $scope.setCurrentUser(authResult.userProfile);
                $state.go("app.dashboard");
            }else{
                $scope.result = authResult.authFailureReason;
                $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
            }
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        });
    };
});