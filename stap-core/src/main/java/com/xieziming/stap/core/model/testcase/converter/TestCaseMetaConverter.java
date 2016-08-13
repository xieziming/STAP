package com.xieziming.stap.core.model.testcase.converter;

import com.xieziming.stap.core.model.testcase.dto.TestCaseMetaDto;
import com.xieziming.stap.core.model.testcase.pojo.TestCaseMeta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class TestCaseMetaConverter {

    public List<TestCaseMetaDto> convertAll(List<TestCaseMeta> testCaseMetaList) {
        List<TestCaseMetaDto> testCaseMetaDtoList = new ArrayList<TestCaseMetaDto>();
        for (TestCaseMeta testCaseMeta : testCaseMetaList){
            testCaseMetaDtoList.add(convert(testCaseMeta));
        }
        return testCaseMetaDtoList;
    }

    public TestCaseMetaDto convert(TestCaseMeta testCaseMeta) {
        TestCaseMetaDto testCaseMetaDto = new TestCaseMetaDto();
        testCaseMetaDto.setId(testCaseMeta.getId());
        testCaseMetaDto.setMetaType(testCaseMeta.getMetaType());
        testCaseMetaDto.setMetaKey(testCaseMeta.getMetaKey());
        testCaseMetaDto.setMetaValue(testCaseMeta.getMetaValue());
        testCaseMetaDto.setLastUpdate(testCaseMeta.getLastUpdate());
        return testCaseMetaDto;
    }
}
