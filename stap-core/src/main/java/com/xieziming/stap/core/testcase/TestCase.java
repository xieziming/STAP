package com.xieziming.stap.core.testcase;

import com.xieziming.stap.core.testcase.raw.*;
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
public class TestCase {
    private Integer id;
    private List<RawTestCaseMeta> rawTestCaseMeta;
    private List<RawTestCaseRelation> rawTestCaseRelation;
    private List<RawTestData> rawTestDataList;
    private List<RawTestStep> rawTestStepList;
}
