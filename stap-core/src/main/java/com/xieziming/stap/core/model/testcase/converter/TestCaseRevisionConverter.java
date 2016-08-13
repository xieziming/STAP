package com.xieziming.stap.core.model.testcase.converter;

import com.xieziming.stap.core.model.testcase.dao.TestActionRevisionDao;
import com.xieziming.stap.core.model.testcase.dao.TestCaseRevisionDao;
import com.xieziming.stap.core.model.testcase.dao.TestDataDefinitionRevisionDao;
import com.xieziming.stap.core.model.testcase.dto.TestCaseRevisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class TestCaseRevisionConverter {
    @Autowired
    private TestCaseRevisionDao testCaseRevisionDao;
    @Autowired
    private TestActionRevisionDao testActionRevisionDao;
    @Autowired
    private TestDataDefinitionRevisionDao testDataDefinitionRevisionDao;

    public TestCaseRevisionDto convert(int testCaseId) {
        TestCaseRevisionDto testCaseRevisionDto = new TestCaseRevisionDto();
        testCaseRevisionDto.setTestCaseRevisionList(testCaseRevisionDao.findAll(testCaseId));
        testCaseRevisionDto.setTestDataDefinitionRevisionList(testDataDefinitionRevisionDao.findAllByTestCaseId(testCaseId));
        testCaseRevisionDto.setTestActionRevisionList(testActionRevisionDao.findAllByTestCase(testCaseId));
        return testCaseRevisionDto;
    }
}
