package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.common.StapLog;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionPlanLog {
    private Integer id;
    private ExecutionPlan executionPlan;
    private StapLog stapLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExecutionPlan getExecutionPlan() {
        return executionPlan;
    }

    public void setExecutionPlan(ExecutionPlan executionPlan) {
        this.executionPlan = executionPlan;
    }

    public StapLog getStapLog() {
        return stapLog;
    }

    public void setStapLog(StapLog stapLog) {
        this.stapLog = stapLog;
    }
}
