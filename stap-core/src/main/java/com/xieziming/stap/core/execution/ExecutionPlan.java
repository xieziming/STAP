package com.xieziming.stap.core.execution;

import java.util.List;

/**
 * Created by Suny on 5/8/16.
 */
public class ExecutionPlan extends BasicExecutionPlan {
    private List<ExecutionPlanMeta> executionPlanMetaList;
    private List<ExecutionPlanLog> executionPlanLogList;
    private List<BasicExecution> basicExecutionList;

    public ExecutionPlan(BasicExecutionPlan basicExecutionPlan) {
        super.setId(basicExecutionPlan.getId());
        super.setName(basicExecutionPlan.getName());
        super.setRemark(basicExecutionPlan.getRemark());
        super.setStatus(basicExecutionPlan.getStatus());
        super.setLastUpdate(basicExecutionPlan.getLastUpdate());
    }

    public List<ExecutionPlanMeta> getExecutionPlanMetaList() {
        return executionPlanMetaList;
    }

    public void setExecutionPlanMetaList(List<ExecutionPlanMeta> executionPlanMetaList) {
        this.executionPlanMetaList = executionPlanMetaList;
    }

    public List<ExecutionPlanLog> getExecutionPlanLogList() {
        return executionPlanLogList;
    }

    public void setExecutionPlanLogList(List<ExecutionPlanLog> executionPlanLogList) {
        this.executionPlanLogList = executionPlanLogList;
    }

    public List<BasicExecution> getBasicExecutionList() {
        return basicExecutionList;
    }

    public void setBasicExecutionList(List<BasicExecution> basicExecutionList) {
        this.basicExecutionList = basicExecutionList;
    }
}
