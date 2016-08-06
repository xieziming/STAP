package com.xieziming.stap.core.model.execution.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionOutputTextDto {
    private Integer id;
    private String type;
    private String field;
    private String value;
    private String remark;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}