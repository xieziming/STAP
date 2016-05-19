package com.xieziming.stap.core.teststep.parsers;

import com.xieziming.stap.core.teststep.TestStepParameterParser;

/**
 * Created by Suny on 5/20/16.
 */
public class DefaultParser implements TestStepParameterParser {
    public Object parse(Object object) {
        return object;
    }
}
