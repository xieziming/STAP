package com.xieziming.stap.core.common;

/**
 * Created by Suny on 5/21/16.
 */
public enum ExecutionConstants {
    STARTED("Started"),
    INPROGRESS("InProcess"),
    COMPLETED("Completed"),
    OPENED("Opened"),
    CLOSED("Closed"),
    PASS("Pass"),
    FAILE("Fail");

    private String constant;

    ExecutionConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
