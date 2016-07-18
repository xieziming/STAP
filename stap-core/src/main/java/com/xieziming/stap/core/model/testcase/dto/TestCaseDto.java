package com.xieziming.stap.core.model.testcase.dto;

import com.xieziming.stap.core.model.common.pojo.Revision;
import com.xieziming.stap.core.model.testcase.pojo.TestCaseMeta;
import com.xieziming.stap.core.model.testcase.pojo.TestCaseRelation;
import com.xieziming.stap.core.model.testcase.pojo.TestData;
import com.xieziming.stap.core.model.testcase.pojo.TestStep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Suny on 7/17/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDto {
    private Integer id;
    private List<TestCaseMeta> testCaseMetaList;
    private List<TestCaseRelation> testCaseRelationList;
    private List<Revision> revisionList;
    private List<TestStep> testStepList;
    private List<TestData> testDataList;
}
