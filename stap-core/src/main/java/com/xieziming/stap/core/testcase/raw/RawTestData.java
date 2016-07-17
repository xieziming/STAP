package com.xieziming.stap.core.testcase.raw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawTestData {
    private Integer id;
    private Integer testCaseId;
    private Integer testDataDefinitionId;
}
