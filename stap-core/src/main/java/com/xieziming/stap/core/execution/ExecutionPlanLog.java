package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.common.StapLog;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionPlanLog {
    private Integer id;
    private BasicExecutionPlan basicExecutionPlan;
    private StapLog stapLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicExecutionPlan getBasicExecutionPlan() {
        return basicExecutionPlan;
    }

    public void setBasicExecutionPlan(BasicExecutionPlan basicExecutionPlan) {
        this.basicExecutionPlan = basicExecutionPlan;
    }

    public StapLog getStapLog() {
        return stapLog;
    }

    public void setStapLog(StapLog stapLog) {
        this.stapLog = stapLog;
    }
}
