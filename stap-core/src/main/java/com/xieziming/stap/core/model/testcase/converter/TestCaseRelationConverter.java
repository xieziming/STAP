package com.xieziming.stap.core.model.testcase.converter;

import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import com.xieziming.stap.core.model.testcase.dto.TestCaseRelationDto;
import com.xieziming.stap.core.model.testcase.pojo.TestCaseRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class TestCaseRelationConverter {
    @Autowired
    private TestCaseDao testCaseDao;

    public List<TestCaseRelationDto> convertAll(List<TestCaseRelation> testCaseRelationList) {
        List<TestCaseRelationDto> testCaseRelationDtoList = new ArrayList<TestCaseRelationDto>();
        for (TestCaseRelation testCaseRelation : testCaseRelationList){
            testCaseRelationDtoList.add(convert(testCaseRelation));
        }
        return testCaseRelationDtoList;
    }

    public TestCaseRelationDto convert(TestCaseRelation testCaseRelation) {
        TestCaseRelationDto testCaseRelationDto = new TestCaseRelationDto();
        testCaseRelationDto.setId(testCaseRelation.getId());
        testCaseRelationDto.setRelatedTestCase(testCaseDao.findById(testCaseRelation.getTestCaseId()));
        testCaseRelationDto.setRelation(testCaseRelation.getRelation());
        testCaseRelationDto.setRemark(testCaseRelation.getRemark());
        return testCaseRelationDto;
    }
}
