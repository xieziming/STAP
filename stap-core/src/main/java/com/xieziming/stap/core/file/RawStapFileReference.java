package com.xieziming.stap.core.file;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Suny on 7/17/16.
 */
public class RawStapFileReference {
    private Integer id;
    private String name;
    private String uri;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}
