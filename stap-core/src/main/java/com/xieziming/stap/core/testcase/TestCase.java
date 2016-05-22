package com.xieziming.stap.core.testcase;

import java.util.List;

/**
 * Created by Suny on 5/8/16.
 */
public class TestCase extends BasicTestCase {
    private List<TestCaseMeta> testCaseMetaList;
    private List<TestData> testDataList;
    private List<TestStep> testStepList;

    public TestCase(BasicTestCase basicTestCase) {
        super.setId(basicTestCase.getId());
        super.setBasicParentTestCase(basicTestCase.getBasicParentTestCase());
        super.setName(basicTestCase.getName());
        super.setRemark(basicTestCase.getRemark());
        super.setLastUpdate(basicTestCase.getLastUpdate());
    }

    public List<TestCaseMeta> getTestCaseMetaList() {
        return testCaseMetaList;
    }

    public void setTestCaseMetaList(List<TestCaseMeta> testCaseMetaList) {
        this.testCaseMetaList = testCaseMetaList;
    }

    public List<TestData> getTestDataList() {
        return testDataList;
    }

    public void setTestDataList(List<TestData> testDataList) {
        this.testDataList = testDataList;
    }

    public List<TestStep> getTestStepList() {
        return testStepList;
    }

    public void setTestStepList(List<TestStep> testStepList) {
        this.testStepList = testStepList;
    }
}
