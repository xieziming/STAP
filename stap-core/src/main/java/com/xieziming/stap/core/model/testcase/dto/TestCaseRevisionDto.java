package com.xieziming.stap.core.model.testcase.dto;

import com.xieziming.stap.core.model.testcase.pojo.TestActionRevision;
import com.xieziming.stap.core.model.testcase.pojo.TestCaseRevision;
import com.xieziming.stap.core.model.testcase.pojo.TestDataDefinitionRevision;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Suny on 8/7/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseRevisionDto {
    private List<TestCaseRevision> testCaseRevisionList;
    private List<TestDataDefinitionRevision> testDataDefinitionRevisionList;
    private List<TestActionRevision> testActionRevisionList;
}
