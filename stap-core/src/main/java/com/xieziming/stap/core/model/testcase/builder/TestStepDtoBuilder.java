package com.xieziming.stap.core.model.testcase.builder;

import com.xieziming.stap.core.model.testcase.dao.TestActionDao;
import com.xieziming.stap.core.model.testcase.dto.TestStepDto;
import com.xieziming.stap.core.model.testcase.pojo.TestStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class TestStepDtoBuilder {
    @Autowired
    private TestActionDao testActionDao;

    public List<TestStepDto> buildAll(List<TestStep> testStepList) {
        List<TestStepDto> testStepDtoList = new ArrayList<TestStepDto>();
        for (TestStep testStep : testStepList){
            testStepDtoList.add(build(testStep));
        }
        return testStepDtoList;
    }

    public TestStepDto build(TestStep testStep) {
        TestStepDto testStepDto = new TestStepDto();
        testStepDto.setId(testStep.getId());
        testStepDto.setStepOrder(testStep.getStepOrder());
        testStepDto.setTestAction(testActionDao.findById(testStep.getTestActionId()));
        testStepDto.setParameter(testStep.getParameter());
        return testStepDto;
    }
}
