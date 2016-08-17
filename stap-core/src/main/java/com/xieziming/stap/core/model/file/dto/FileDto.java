package com.xieziming.stap.core.model.file.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateDeserializer;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 8/14/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private Integer id;
    private String name;
    private String url;
    private List<FileMetaDto> fileMetaDtoList;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date lastUpdate;
}
