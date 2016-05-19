package com.xieziming.stap.core.teststep;

import com.xieziming.stap.core.teststep.parsers.DefaultParser;

/**
 * Created by Suny on 5/8/16.
 */
public class TestStepParameter {
    private String parameter;
    private TestStepParameterParser testStepParameterParser;

    public TestStepParameter(String parameter) {
       this(parameter, new DefaultParser());
    }

    public TestStepParameter(String parameter, TestStepParameterParser testStepParameterParser){
        this.parameter = parameter;
        this.testStepParameterParser = testStepParameterParser;
    }

    public Object getParameter() {
        return testStepParameterParser.parse(parameter);
    }
}
