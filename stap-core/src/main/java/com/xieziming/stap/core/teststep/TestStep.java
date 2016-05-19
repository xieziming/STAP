package com.xieziming.stap.core.teststep;

import com.xieziming.stap.core.testaction.TestAction;
import com.xieziming.stap.core.testcase.TestCase;

/**
 * Created by Suny on 5/8/16.
 */
public class TestStep {
    private Integer id;
    private TestCase testCase;
    private Integer stepOrder;
    private TestAction testAction;
    private TestStepParameter testStepParameter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
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
