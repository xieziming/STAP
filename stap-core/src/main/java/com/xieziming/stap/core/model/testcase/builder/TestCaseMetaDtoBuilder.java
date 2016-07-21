package com.xieziming.stap.core.model.testcase.builder;

import com.xieziming.stap.core.model.testcase.dto.TestCaseMetaDto;
import com.xieziming.stap.core.model.testcase.pojo.TestCaseMeta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class TestCaseMetaDtoBuilder {

    public List<TestCaseMetaDto> buildAll(List<TestCaseMeta> testCaseMetaList) {
        List<TestCaseMetaDto> testCaseMetaDtoList = new ArrayList<TestCaseMetaDto>();
        for (TestCaseMeta testCaseMeta : testCaseMetaList){
            testCaseMetaDtoList.add(build(testCaseMeta));
        }
        return testCaseMetaDtoList;
    }

    public TestCaseMetaDto build(TestCaseMeta testCaseMeta) {
        TestCaseMetaDto testCaseMetaDto = new TestCaseMetaDto();
        testCaseMetaDto.setId(testCaseMeta.getId());
        testCaseMetaDto.setMetaKey(testCaseMeta.getMetaKey());
        testCaseMetaDto.setMetaValue(testCaseMeta.getMetaValue());
        testCaseMetaDto.setLastUpdate(testCaseMeta.getLastUpdate());
        return testCaseMetaDto;
    }
}
