package com.xieziming.stap.core.testcase;

/**
 * Created by Suny on 5/31/16.
 */
public class TestCaseDependence extends BasicTestCaseDependence {
    private TestCase testCase;
    private TestCase dependentTestCase;

    public TestCaseDependence(BasicTestCaseDependence basicTestCaseDependence) {
        super.setBasicTestCase(basicTestCaseDependence.getBasicTestCase());
        super.setBasicDependentTestCase(basicTestCaseDependence.getBasicDependentTestCase());
        super.setRemark(basicTestCaseDependence.getRemark());
    }

    public TestCase getBasicTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestCase getBasicDependentTestCase() {
        return dependentTestCase;
    }

    public void setDependentTestCase(TestCase dependentTestCase) {
        this.dependentTestCase = dependentTestCase;
    }
}
