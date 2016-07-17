package com.xieziming.stap.core.testcase.raw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawTestCaseRelation {
    private Integer id;
    private Integer testCaseId;
    private Integer RelatedTestCaseId;
    private String relation;
    private String remark;
}
