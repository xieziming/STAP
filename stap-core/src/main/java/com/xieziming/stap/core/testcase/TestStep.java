package com.xieziming.stap.core.testcase;

/**
 * Created by Suny on 5/8/16.
 */
public class TestStep {
    private Integer id;
    private BasicTestCase basicTestCase;
    private Integer stepOrder;
    private TestAction testAction;
    private String testStepParameter;

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

    public String getTestStepParameter() {
        return testStepParameter;
    }

    public void setTestStepParameter(String testStepParameter) {
        this.testStepParameter = testStepParameter;
    }
}
