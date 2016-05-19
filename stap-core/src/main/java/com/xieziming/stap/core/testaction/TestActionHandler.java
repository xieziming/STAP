package com.xieziming.stap.core.testaction;

import com.xieziming.stap.core.testaction.parsers.DefaultParser;

/**
 * Created by Suny on 5/20/16.
 */
public class TestActionHandler {
    private String handler;
    private TestActionHandlerParser testActionHandlerParser;

    public TestActionHandler(String handler, TestActionHandlerParser testActionHandlerParser) {
        this.handler = handler;
        this.testActionHandlerParser = testActionHandlerParser;
    }

    public TestActionHandler(String handler) {
        this(handler, new DefaultParser());
    }

    public Object getHandler() {
        return testActionHandlerParser.parse(handler);
    }
}
