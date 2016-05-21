package com.xieziming.stap.execution.config;

/**
 * Created by Suny on 5/21/16.
 */
public enum ExecutionConstants {
    STARTED("Started"),
    INPROGRESS("InProgress"),
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
