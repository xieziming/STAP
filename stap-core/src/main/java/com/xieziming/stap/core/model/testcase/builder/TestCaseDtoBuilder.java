package com.xieziming.stap.core.model.testcase.builder;

import com.xieziming.stap.core.model.testcase.dao.*;
import com.xieziming.stap.core.model.testcase.dto.TestCaseDto;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class TestCaseDtoBuilder {
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;
    @Autowired
    private TestCaseRelationDao testCaseRelationDao;
    @Autowired
    private TestDataDao testDataDao;
    @Autowired
    private TestStepDao testStepDao;
    @Autowired
    private TestCaseMetaDtoBuilder testCaseMetaDtoBuilder;
    @Autowired
    private TestCaseRelationDtoBuilder testCaseRelationDtoBuilder;
    @Autowired
    private TestStepDtoBuilder testStepDtoBuilder;
    @Autowired
    private TestDataDtoBuilder testDataDtoBuilder;

    public List<TestCaseDto> buildAll(List<TestCase> testCaseList) {
        List<TestCaseDto> testCaseDtoList = new ArrayList<TestCaseDto>();
        for (TestCase testCase : testCaseList){
            testCaseDtoList.add(build(testCase));
        }
        return testCaseDtoList;
    }

    public TestCaseDto build(TestCase testCase) {
        TestCaseDto testCaseDto = new TestCaseDto();
        testCaseDto.setTestCase(testCase);
        testCaseDto.setTestCaseMetaDtoList(testCaseMetaDtoBuilder.buildAll(testCaseMetaDao.findAllByTestCaseId(testCase.getId())));
        testCaseDto.setTestCaseRelationDtoList(testCaseRelationDtoBuilder.buildAll(testCaseRelationDao.findAllByTestCaseId(testCase.getId())));
        testCaseDto.setTestDataDtoList(testDataDtoBuilder.buildAll(testDataDao.findAllByTestCaseId(testCase.getId())));
        testCaseDto.setTestStepDtoList(testStepDtoBuilder.buildAll(testStepDao.findAllByTestCaseId(testCase.getId())));
        return testCaseDto;
    }
}
