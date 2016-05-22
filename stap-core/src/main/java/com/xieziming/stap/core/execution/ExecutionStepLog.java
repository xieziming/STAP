package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.common.StapLog;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionStepLog {
    private Integer id;
    private BasicExecutionStep basicExecutionStep;
    private StapLog stapLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicExecutionStep getBasicExecutionStep() {
        return basicExecutionStep;
    }

    public void setBasicExecutionStep(BasicExecutionStep basicExecutionStep) {
        this.basicExecutionStep = basicExecutionStep;
    }

    public StapLog getStapLog() {
        return stapLog;
    }

    public void setStapLog(StapLog stapLog) {
        this.stapLog = stapLog;
    }
}
