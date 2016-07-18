package com.xieziming.stap.core.testcase.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDataDefinition {
    private Integer id;
    private String field;
    private String value;
    private Integer fileId;
    private String remark;
    private String type;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}
