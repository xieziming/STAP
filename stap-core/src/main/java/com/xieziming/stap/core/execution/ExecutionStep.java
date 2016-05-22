package com.xieziming.stap.core.execution;

import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionStep extends BasicExecutionStep{
    private List<ExecutionStepOutputText> executionStepOutputTextList;
    private List<ExecutionStepOutputFile> executionStepOutputFileList;
    private List<ExecutionStepLog> executionStepLogList;

    public ExecutionStep(BasicExecutionStep basicExecutionStep) {
        super.setId(basicExecutionStep.getId());
        super.setBasicExecution(basicExecutionStep.getBasicExecution());
        super.setTestStep(basicExecutionStep.getTestStep());
        super.setStartTime(basicExecutionStep.getStartTime());
        super.setEndTime(basicExecutionStep.getEndTime());
        super.setStatus(basicExecutionStep.getStatus());
        super.setResult(basicExecutionStep.getResult());
        super.setRemark(basicExecutionStep.getRemark());
    }

    public List<ExecutionStepOutputText> getExecutionStepOutputTextList() {
        return executionStepOutputTextList;
    }

    public void setExecutionStepOutputTextList(List<ExecutionStepOutputText> executionStepOutputTextList) {
        this.executionStepOutputTextList = executionStepOutputTextList;
    }

    public List<ExecutionStepOutputFile> getExecutionStepOutputFileList() {
        return executionStepOutputFileList;
    }

    public void setExecutionStepOutputFileList(List<ExecutionStepOutputFile> executionStepOutputFileList) {
        this.executionStepOutputFileList = executionStepOutputFileList;
    }

    public List<ExecutionStepLog> getExecutionStepLogList() {
        return executionStepLogList;
    }

    public void setExecutionStepLogList(List<ExecutionStepLog> executionStepLogList) {
        this.executionStepLogList = executionStepLogList;
    }

}
