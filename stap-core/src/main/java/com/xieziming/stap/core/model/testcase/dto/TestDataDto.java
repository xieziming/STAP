package com.xieziming.stap.core.model.testcase.dto;

import com.xieziming.stap.core.model.testcase.pojo.TestDataDefinition;
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
public class TestDataDto {
    private Integer id;
    private Integer testCaseId;
    private List<TestDataDefinition> testDataDefinitionList;
}
