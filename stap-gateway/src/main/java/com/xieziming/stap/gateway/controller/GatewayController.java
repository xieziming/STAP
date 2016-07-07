package com.xieziming.stap.gateway.controller;

import com.google.common.cache.Cache;
import com.xieziming.stap.gateway.mode.LoginResult;
import com.xieziming.stap.gateway.service.GatewayService;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Suny on 7/5/16.
 */
@Controller
@PropertySources(
        {
                @PropertySource("classpath:stap-gateway.properties")
        }
)
public class GatewayController {
    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);
    private GatewayService gatewayService;
    private Cache<String, LoginResult> userCache;

    @Autowired
    public GatewayController(GatewayService gatewayService, Cache<String, LoginResult> userCache) {
        this.gatewayService = gatewayService;
        this.userCache = userCache;
    }

    @RequestMapping("/**")
    public ResponseEntity<?> getResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("got request for " + req.getRequestURI());
        //UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");
        //if (userProfile == null) return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

        //userCache.getIfPresent(userProfile.getUserName());
        CloseableHttpResponse httpResponse = (CloseableHttpResponse) gatewayService.getResponse(req, resp);
        IOUtils.closeQuietly(req.getInputStream());
        String response = IOUtils.toString(httpResponse.getEntity().getContent());

        httpResponse.close();
        resp.setContentType(httpResponse.getEntity().getContentType().getValue());
        if(httpResponse.getEntity().getContentType().getValue().contains("stream")) {
            return new ResponseEntity<>(response.getBytes(), HttpStatus.valueOf(httpResponse.getStatusLine().getStatusCode()));
        }else{
            return new ResponseEntity<>(response, HttpStatus.valueOf(httpResponse.getStatusLine().getStatusCode()));
        }
    }

    @RequestMapping("/resources/**")
    public ResponseEntity<Object> getResourceResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return new ResponseEntity<Object>(IOUtils.toString(req.getServletContext().getResourceAsStream(req.getRequestURI().substring(req.getRequestURI().indexOf("/resources/"))), "UTF-8"), HttpStatus.OK);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
