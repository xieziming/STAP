package com.xieziming.stap.core.model.testcase.dao;

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
public class TestCaseDtoDao {

    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private TestStepDao testStepDao;
    @Autowired
    private TestDataDao testDataDao;
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;
    @Autowired
    private TestCaseRelationDao testCaseRelationDao;

    public List<TestCaseDto> createDto(List<TestCase> testCaseList) {
        List<TestCaseDto> testCaseDtoList = new ArrayList<TestCaseDto>();
        for (TestCase testCase : testCaseList){
            testCaseDtoList.add(createDto(testCase));
        }
        return testCaseDtoList;
    }

    public TestCaseDto createDto(TestCase testCase) {
        TestCaseDto testCaseDto = new TestCaseDto();
        testCaseDto.setTestCaseMetaList(testCaseMetaDao.findAllByTestCaseId(testCase.getId()));
        testCaseDto.setTestCaseRelationList(testCaseRelationDao.findAllByTestCaseId(testCase.getId()));
        testCaseDto.setTestDataList(testDataDao.findAllByTestCaseId(testCase.getId()));
        testCaseDto.setTestStepList(testStepDao.findAllByTestCaseId(testCase.getId()));
        testCaseDto.setTestCase(testCase);
        return testCaseDto;
    }
}
