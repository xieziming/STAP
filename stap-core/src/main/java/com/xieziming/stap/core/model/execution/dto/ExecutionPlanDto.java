package com.xieziming.stap.core.model.execution.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionPlanDto {
    private Integer id;
    private String name;
    private String description;
    private String status;
    private List<ExecutionPlanMetaDto> executionPlanMetaDtoList;
    private List<ExecutionLogDto> executionLogDtoList;
}
