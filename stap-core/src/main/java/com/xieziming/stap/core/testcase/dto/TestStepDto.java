package com.xieziming.stap.core.testcase.dto;

import com.xieziming.stap.core.testcase.pojo.TestAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 7/17/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestStepDto {
    private Integer id;
    private Integer testCaseId;
    private Integer stepOrder;
    private TestAction testAction;
    private String parameter;
}
