package com.xieziming.stap.core.model.testcase.converter;

import com.xieziming.stap.core.model.testcase.dao.TestDataDefinitionDao;
import com.xieziming.stap.core.model.testcase.dto.TestDataDto;
import com.xieziming.stap.core.model.testcase.pojo.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class TestDataConverter {
    @Autowired
    private TestDataDefinitionDao testDataDefinitionDao;

    public List<TestDataDto> convertAll(List<TestData> testDataList) {
        List<TestDataDto> testDataDtoList = new ArrayList<TestDataDto>();
        for (TestData testData : testDataList){
            testDataDtoList.add(convert(testData));
        }
        return testDataDtoList;
    }

    public TestDataDto convert(TestData testData) {
        TestDataDto testDataDto = new TestDataDto();
        testDataDto.setId(testData.getId());
        testDataDto.setTestDataDefinition(testDataDefinitionDao.findById(testData.getTestDataDefinitionId()));
        return testDataDto;
    }
}
