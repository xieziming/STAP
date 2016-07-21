package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.model.execution.dto.ExecutionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 7/21/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionResponse {
    private String approved;
    private ExecutionDto executionDto;
}
