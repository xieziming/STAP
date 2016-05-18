package com.xieziming.stap.core.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/18/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StapFileDetail {
    private Integer id;
    private String path;
    private byte[] content;
    private List<StapMeta> metaList;
    private Date lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public List<StapMeta> getMetaList() {
        return metaList;
    }

    public void setMetaList(List<StapMeta> metaList) {
        this.metaList = metaList;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
