package com.xieziming.stap.core.testcase.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseRelation {
    private Integer id;
    private Integer testCaseId;
    private Integer RelatedTestCaseId;
    private String relation;
    private String remark;
}
