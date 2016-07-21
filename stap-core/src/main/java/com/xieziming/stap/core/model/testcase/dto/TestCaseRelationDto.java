package com.xieziming.stap.core.model.testcase.dto;

import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseRelationDto {
    private Integer id;
    private TestCase relatedTestCase;
    private String relation;
    private String remark;
}
