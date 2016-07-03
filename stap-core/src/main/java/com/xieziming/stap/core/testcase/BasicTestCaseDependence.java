package com.xieziming.stap.core.testcase;

/**
 * Created by Suny on 5/31/16.
 */
public class BasicTestCaseDependence {
    private Integer id;
    private BasicTestCase basicTestCase;
    private BasicTestCase basicDependentTestCase;
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

    public BasicTestCase getBasicDependentTestCase() {
        return basicDependentTestCase;
    }

    public void setBasicDependentTestCase(BasicTestCase basicDependentTestCase) {
        this.basicDependentTestCase = basicDependentTestCase;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
