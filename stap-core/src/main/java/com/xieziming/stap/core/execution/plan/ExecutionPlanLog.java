package com.xieziming.stap.core.execution.plan;

import com.xieziming.stap.core.common.StapLog;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionPlanLog {
    private Integer id;
    private Integer executionPlanId;
    private StapLog stapLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExecutionPlanId() {
        return executionPlanId;
    }

    public void setExecutionPlanId(Integer executionPlanId) {
        this.executionPlanId = executionPlanId;
    }

    public StapLog getStapLog() {
        return stapLog;
    }

    public void setStapLog(StapLog stapLog) {
        this.stapLog = stapLog;
    }
}
