package com.xieziming.stap.core.model.testcase.dto;

import com.xieziming.stap.core.model.testcase.pojo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Suny on 7/17/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDto {
    private TestCase testCase;
    private List<TestCaseMetaDto> testCaseMetaDtoList;
    private List<TestCaseRelationDto> testCaseRelationDtoList;
    private List<TestStepDto> testStepDtoList;
    private List<TestDataDto> testDataDtoList;
}
