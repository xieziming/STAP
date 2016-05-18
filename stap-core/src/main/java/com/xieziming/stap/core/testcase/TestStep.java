package com.xieziming.stap.core.testcase;

/**
 * Created by Suny on 5/8/16.
 */
public class TestStep {
    private Integer id;
    private Integer testCaseId;
    private Integer stepOrder;
    private TestAction testAction;
    private TestStepParameter testStepParameter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public TestAction getTestAction() {
        return testAction;
    }

    public void setTestAction(TestAction testAction) {
        this.testAction = testAction;
    }

    public TestStepParameter getTestStepParameter() {
        return testStepParameter;
    }

    public void setTestStepParameter(TestStepParameter testStepParameter) {
        this.testStepParameter = testStepParameter;
    }
}
