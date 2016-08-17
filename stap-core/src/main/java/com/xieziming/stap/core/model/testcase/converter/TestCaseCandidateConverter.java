package com.xieziming.stap.core.model.testcase.converter;

import com.xieziming.stap.core.model.testcase.dao.TestCaseMetaDao;
import com.xieziming.stap.core.model.testcase.dao.TestCaseRelationDao;
import com.xieziming.stap.core.model.testcase.dto.TestCaseCandidateDto;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class TestCaseCandidateConverter {
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;
    @Autowired
    private TestCaseRelationDao testCaseRelationDao;
    @Autowired
    private TestCaseMetaConverter testCaseMetaConverter;
    @Autowired
    private TestCaseRelationConverter testCaseRelationConverter;

    public List<TestCaseCandidateDto> convertAll(List<TestCase> testCaseList) {
        List<TestCaseCandidateDto> testCaseCandidateDtoList = new ArrayList<TestCaseCandidateDto>();
        for (TestCase testCase : testCaseList){
            testCaseCandidateDtoList.add(convert(testCase));
        }
        return testCaseCandidateDtoList;
    }

    public TestCaseCandidateDto convert(TestCase testCase) {
        TestCaseCandidateDto testCaseCandidateDto = new TestCaseCandidateDto();
        testCaseCandidateDto.setTestCase(testCase);
        testCaseCandidateDto.setTestCaseMetaDtoList(testCaseMetaConverter.convertAll(testCaseMetaDao.findAllByTestCaseId(testCase.getId())));
        testCaseCandidateDto.setTestCaseRelationDtoList(testCaseRelationConverter.convertAll(testCaseRelationDao.findAllByTestCaseId(testCase.getId())));
        return testCaseCandidateDto;
    }
}