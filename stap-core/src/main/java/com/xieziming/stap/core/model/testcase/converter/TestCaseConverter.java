package com.xieziming.stap.core.model.testcase.converter;

import com.xieziming.stap.core.model.execution.converter.ExecutionBriefConverter;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.testcase.dao.TestCaseMetaDao;
import com.xieziming.stap.core.model.testcase.dao.TestCaseRelationDao;
import com.xieziming.stap.core.model.testcase.dao.TestDataDao;
import com.xieziming.stap.core.model.testcase.dao.TestStepDao;
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
public class TestCaseConverter {
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;
    @Autowired
    private TestCaseRelationDao testCaseRelationDao;
    @Autowired
    private TestDataDao testDataDao;
    @Autowired
    private TestStepDao testStepDao;
    @Autowired
    private TestCaseMetaConverter testCaseMetaConverter;
    @Autowired
    private TestCaseRelationConverter testCaseRelationConverter;
    @Autowired
    private TestStepConverter testStepConverter;
    @Autowired
    private TestDataConverter testDataConverter;
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private ExecutionBriefConverter executionBriefConverter;

    public List<TestCaseDto> convertAll(List<TestCase> testCaseList) {
        List<TestCaseDto> testCaseDtoList = new ArrayList<TestCaseDto>();
        for (TestCase testCase : testCaseList){
            testCaseDtoList.add(convert(testCase));
        }
        return testCaseDtoList;
    }

    public TestCaseDto convert(TestCase testCase) {
        TestCaseDto testCaseDto = new TestCaseDto();
        testCaseDto.setTestCase(testCase);
        testCaseDto.setTestCaseMetaDtoList(testCaseMetaConverter.convertAll(testCaseMetaDao.findAllByTestCaseId(testCase.getId())));
        testCaseDto.setTestCaseRelationDtoList(testCaseRelationConverter.convertAll(testCaseRelationDao.findAllByTestCaseId(testCase.getId())));
        testCaseDto.setTestDataDtoList(testDataConverter.convertAll(testDataDao.findAllByTestCaseId(testCase.getId())));
        testCaseDto.setTestStepDtoList(testStepConverter.convertAll(testStepDao.findAllByTestCaseId(testCase.getId())));
        testCaseDto.setExecutionBriefDtoList(executionBriefConverter.convertAll(executionDao.findAllByTestCaseId(testCase.getId())));
        return testCaseDto;
    }
}
