package com.xieziming.stap.gateway.mode;

/**
 * Created by Suny on 7/6/16.
 */
public class LoginResult {
    private boolean loginSuccess;
    private LoginFailureReason failureReason;
    private String token;
    private UserProfile userProfile;

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public LoginFailureReason getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(LoginFailureReason failureReason) {
        this.failureReason = failureReason;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
