package com.xieziming.stap.core.common.pojo;

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
public class Revision {
    private Integer id;
    private String source;
    private String reference;
    private String content;
    private String operator;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date time;
}
