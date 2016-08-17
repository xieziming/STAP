package com.xieziming.stap.gateway.controller;

import com.xieziming.stap.core.security.auth.AuthService;
import com.xieziming.stap.core.security.auth.CredentialCache;
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
import java.util.Enumeration;

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
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private AuthService authService;

    private final String UTF8 = ";charset=UTF-8";


    @RequestMapping("/**")
    public ResponseEntity<?> getResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("got distribute for " + req.getRequestURI());
        if(!isAuthorized(req)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        CloseableHttpResponse httpResponse = (CloseableHttpResponse) gatewayService.getResponse(req, resp);
        IOUtils.closeQuietly(req.getInputStream());
        byte[] response = IOUtils.toByteArray(httpResponse.getEntity().getContent());

        httpResponse.close();
        return new ResponseEntity<Object>(response, HttpStatus.valueOf(httpResponse.getStatusLine().getStatusCode()));
    }

    @RequestMapping("/resources/**")
    public ResponseEntity<Object> getResourceResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return new ResponseEntity<Object>(IOUtils.toString(req.getServletContext().getResourceAsStream(req.getRequestURI().substring(req.getRequestURI().indexOf("/resources/"))), "UTF-8"), HttpStatus.OK);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private boolean isAuthorized(HttpServletRequest req){
        Enumeration<String> headerNameEnum = req.getHeaderNames();
        String name = null, token = null;
        while(headerNameEnum.hasMoreElements()){
            String headerName = headerNameEnum.nextElement();
            if(headerName.matches("Stap-User")){
                name = req.getHeader(headerName);
            }

            if(headerName.matches("Stap-Token")){
                token = req.getHeader(headerName);
            }
        }

        if(name != null && token != null && authService.hasAuth(new CredentialCache(name, token)).isAuthSuccess()) return true;
        return false;
    }
}
