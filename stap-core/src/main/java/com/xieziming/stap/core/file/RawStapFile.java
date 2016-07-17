package com.xieziming.stap.core.file;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Suny on 7/17/16.
 */
public class RawStapFile {
    private Integer id;
    private String path;
    private byte[] content;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}
