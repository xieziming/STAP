package com.xieziming.stap.core.model.testcase.converter;

import com.xieziming.stap.core.model.file.converter.FileConverter;
import com.xieziming.stap.core.model.file.dao.FileDao;
import com.xieziming.stap.core.model.testcase.dto.TestDataDefinitionDto;
import com.xieziming.stap.core.model.testcase.pojo.TestDataDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class TestDataDefinitionConverter {
    @Autowired
    private
    FileConverter fileConverter;
    @Autowired
    private FileDao fileDao;

    public List<TestDataDefinitionDto> convertAll(List<TestDataDefinition> testDataDefinitionList) {
        List<TestDataDefinitionDto> testDataDefinitionDtoList = new ArrayList<TestDataDefinitionDto>();
        for (TestDataDefinition testDataDefinition : testDataDefinitionList){
            testDataDefinitionDtoList.add(convert(testDataDefinition));
        }
        return testDataDefinitionDtoList;
    }

    public TestDataDefinitionDto convert(TestDataDefinition testDataDefinition) {
        TestDataDefinitionDto testDataDefinitionDto = new TestDataDefinitionDto();
        testDataDefinitionDto.setId(testDataDefinition.getId());
        testDataDefinitionDto.setField(testDataDefinition.getField());
        testDataDefinitionDto.setValue(testDataDefinition.getValue());
        if(testDataDefinition.getFileId() > 0) {
            testDataDefinitionDto.setFileDto(fileConverter.convert(fileDao.findById(testDataDefinition.getFileId())));
        }
        testDataDefinitionDto.setRemark(testDataDefinition.getRemark());
        testDataDefinitionDto.setType(testDataDefinition.getType());
        testDataDefinitionDto.setLastUpdate(testDataDefinition.getLastUpdate());
        return testDataDefinitionDto;
    }
}
