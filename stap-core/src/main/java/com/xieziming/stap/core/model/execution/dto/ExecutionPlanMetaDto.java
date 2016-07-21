package com.xieziming.stap.core.model.execution.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 7/21/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionPlanMetaDto {
    private Integer id;
    private String metaKey;
    private String metaValue;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}
