package com.xieziming.stap.core.model.execution.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.model.execution.pojo.ExecutionContext;
import com.xieziming.stap.core.model.testcase.dto.TestCaseDto;
import com.xieziming.stap.core.util.JsonDateDeserializer;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 7/19/16.
 */
@Data
@NoArgsConstructor
public class ExecutionDto {
    private Integer id;
    private ExecutionPlanDto executionPlanDto;
    private ExecutionContext executionContext;
    private TestCaseDto testCaseDto;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date startTime;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date endTime;
    private String status;
    private String result;
    private String remark;
    private List<ExecutionLogDto> executionLogDtoList;
    private List<ExecutionStepDto> executionStepDtoList;
    private List<ExecutionOutputTextDto> executionOutputTextDtoList;
    private List<ExecutionOutputFileDto> executionOutputFileDtoList;
}
