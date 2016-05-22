package com.xieziming.stap.core.execution;

import java.util.List;

/**
 * Created by Suny on 5/8/16.
 */
public class Execution extends BasicExecution{
    private List<ExecutionStep> executionStepList;
    private List<ExecutionLog> executionLogList;

    public Execution(BasicExecution basicExecution) {
        super.setId(basicExecution.getId());
        super.setBasicExecutionPlan(basicExecution.getBasicExecutionPlan());
        super.setBasicTestCase(basicExecution.getBasicTestCase());
        super.setExecutionContext(basicExecution.getExecutionContext());
        super.setStartTime(basicExecution.getStartTime());
        super.setEndTime(basicExecution.getEndTime());
        super.setStatus(basicExecution.getStatus());
        super.setResult(basicExecution.getResult());
        super.setRemark(basicExecution.getRemark());
    }

    public List<ExecutionStep> getExecutionStepList() {
        return executionStepList;
    }

    public void setExecutionStepList(List<ExecutionStep> executionStepList) {
        this.executionStepList = executionStepList;
    }

    public List<ExecutionLog> getExecutionLogList() {
        return executionLogList;
    }

    public void setExecutionLogList(List<ExecutionLog> executionLogList) {
        this.executionLogList = executionLogList;
    }
}
