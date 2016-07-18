package com.xieziming.stap.core.model.testcase.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestData {
    private Integer id;
    private Integer testCaseId;
    private Integer testDataDefinitionId;
}
