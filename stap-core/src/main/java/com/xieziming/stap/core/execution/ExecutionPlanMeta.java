package com.xieziming.stap.core.execution;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.common.StapMeta;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionPlanMeta {
    private Integer id;
    private BasicExecutionPlan basicExecutionPlan;
    private StapMeta stapMeta;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;

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

    public StapMeta getStapMeta() {
        return stapMeta;
    }

    public void setStapMeta(StapMeta stapMeta) {
        this.stapMeta = stapMeta;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
