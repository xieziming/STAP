package com.xieziming.stap.core.execution.plan;

import com.xieziming.stap.core.common.StapMeta;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionPlanMeta {
    private Integer id;
    private Integer executionPlanId;
    private StapMeta stapMeta;

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

    public StapMeta getStapMeta() {
        return stapMeta;
    }

    public void setStapMeta(StapMeta stapMeta) {
        this.stapMeta = stapMeta;
    }
}
