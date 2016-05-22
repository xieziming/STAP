package com.xieziming.stap.core.execution;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.testcase.BasicTestCase;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
public class BasicExecution {
    private Integer id;
    private BasicTestCase basicTestCase;
    private BasicExecutionPlan basicExecutionPlan;
    private ExecutionContext executionContext;
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

    public BasicTestCase getBasicTestCase() {
        return basicTestCase;
    }

    public void setBasicTestCase(BasicTestCase basicTestCase) {
        this.basicTestCase = basicTestCase;
    }

    public BasicExecutionPlan getBasicExecutionPlan() {
        return basicExecutionPlan;
    }

    public void setBasicExecutionPlan(BasicExecutionPlan basicExecutionPlan) {
        this.basicExecutionPlan = basicExecutionPlan;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public void setExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
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
