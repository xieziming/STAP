package com.xieziming.stap.core.model.testcase.pojo;

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
public class TestAction {
    private Integer id;
    private String name;
    private String handler;
    private String remark;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}
