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
public class RawTestStep {
    private Integer id;
    private Integer testCaseId;
    private Integer stepOrder;
    private Integer testActionId;
    private String parameter;
}
