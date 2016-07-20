package com.xieziming.stap.core.model.execution.dto;

import com.xieziming.stap.core.model.testcase.dto.TestCaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 7/21/16.
 */
@Data
@NoArgsConstructor
public class ExecutionDataDto {
    private ExecutionDto executionDto;
    private TestCaseDto testCaseDto;
}
