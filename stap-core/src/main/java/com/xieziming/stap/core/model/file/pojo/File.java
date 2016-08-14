package com.xieziming.stap.core.model.file.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 7/17/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Integer id;
    private String name;
    private String url;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}
