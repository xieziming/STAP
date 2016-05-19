package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.common.StapMeta;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionPlanMeta {
    private Integer id;
    private ExecutionPlan executionPlan;
    private StapMeta stapMeta;

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

    public StapMeta getStapMeta() {
        return stapMeta;
    }

    public void setStapMeta(StapMeta stapMeta) {
        this.stapMeta = stapMeta;
    }
}
