package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.common.StapLog;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionStepLog {
    private Integer id;
    private ExecutionStep executionStep;
    private StapLog stapLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExecutionStep getExecutionStep() {
        return executionStep;
    }

    public void setExecutionStep(ExecutionStep executionStep) {
        this.executionStep = executionStep;
    }

    public StapLog getStapLog() {
        return stapLog;
    }

    public void setStapLog(StapLog stapLog) {
        this.stapLog = stapLog;
    }
}
