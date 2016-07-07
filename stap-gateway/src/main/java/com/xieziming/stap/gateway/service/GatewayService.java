package com.xieziming.stap.gateway.service;

import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Suny on 7/5/16.
 */
public interface GatewayService {
    HttpResponse getResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
