package com.xieziming.stap.core.execution;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.testcase.TestStep;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Suny on 5/22/16.
 */
public class BasicExecutionStep {
    private Integer id;
    private BasicExecution basicExecution;
    private TestStep testStep;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date startTime;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date endTime;
    private String status;
    private String result;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicExecution getBasicExecution() {
        return basicExecution;
    }

    public void setBasicExecution(BasicExecution basicExecution) {
        this.basicExecution = basicExecution;
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
}
