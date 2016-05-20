package com.xieziming.stap.core.execution;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.teststep.TestStep;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionStep {
    private Integer id;
    private Execution execution;
    private TestStep testStep;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date startTime;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date endTime;
    private String status;
    private String result;
    private String remark;
    private List<ExecutionStepOutputText> executionStepOutputTextList;
    private List<ExecutionStepOutputFile> executionStepOutputFileList;
    private List<ExecutionStepLog> executionStepLogList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public TestStep getTestStep() {
        return testStep;
    }

    public void setTestStep(TestStep testStep) {
        this.testStep = testStep;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
