package com.xieziming.stap.core.testaction.parsers;

import com.xieziming.stap.core.testaction.TestActionHandlerParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suny on 5/20/16.
 */
public class ClassMethodParser implements TestActionHandlerParser {
    public Object parse(Object object) {
        String[] arr = ((String) object).split("@");
        Map<String, String> handler = new HashMap<String, String>();
        handler.put("class", arr[0]);
        handler.put("method", arr[1]);
        return handler;
    }
}
