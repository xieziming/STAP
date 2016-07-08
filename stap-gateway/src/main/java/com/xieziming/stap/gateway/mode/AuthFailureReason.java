package com.xieziming.stap.gateway.mode;

/**
 * Created by Suny on 7/6/16.
 */
public enum AuthFailureReason {
    USER_NOT_FOUND("User not found"),
    USER_LOCKED("User is locked"),
    PASSWORD_INCORRECT("Password incorrect"),
    USER_NOT_AUTHORIZED("User not authorized"),
    USER_OR_PASSWORD_INCORRECT("User or password incorrect");

    private String reason;
    private AuthFailureReason(String reason){
        this.reason = reason;
    }

    public String toString(){
        return reason;
    }
}
