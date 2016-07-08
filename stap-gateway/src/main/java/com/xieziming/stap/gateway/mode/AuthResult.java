package com.xieziming.stap.gateway.mode;

/**
 * Created by Suny on 7/6/16.
 */
public class AuthResult {
    private boolean authSuccess;
    private String authFailureReason;
    private java.lang.String token;
    private UserProfile userProfile;

    public boolean isAuthSuccess() {
        return authSuccess;
    }

    public void setAuthSuccess(boolean authSuccess) {
        this.authSuccess = authSuccess;
    }

    public String getAuthFailureReason() {
        return authFailureReason;
    }

    public void setAuthFailureReason(String authFailureReason) {
        this.authFailureReason = authFailureReason;
    }

    public java.lang.String getToken() {
        return token;
    }

    public void setToken(java.lang.String token) {
        this.token = token;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
