package com.xieziming.stap.gateway.mode;

/**
 * Created by Suny on 7/6/16.
 */
public enum LoginFailureReason {
    USER_NOT_FOUND("User not found"),
    USER_LOCKED("User is locked"),
    PASSWORD_INCORRECT("Password incorrect"),
    USER_NOT_AUTHORIZED("User not authorized");

    private String descripton;
    private LoginFailureReason(String description){
        this.descripton = description;
    }

    public String getDescripton(){
        return descripton;
    }
}
