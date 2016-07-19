package com.xieziming.stap.core.model.file.dto;

import com.xieziming.stap.core.model.file.pojo.StapFile;
import com.xieziming.stap.core.model.file.pojo.StapFileMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Suny on 7/19/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StapFileDto {
    private StapFile stapFile;
    private List<StapFileMeta> stapFileMetaList;
}
