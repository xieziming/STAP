package com.xieziming.stap.gateway.service;

import com.xieziming.stap.gateway.mode.UserProfile;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Suny on 7/6/16.
 */
@Component
public class GatewayServiceImpl implements GatewayService{
    private static final Logger log = LoggerFactory.getLogger(GatewayServiceImpl.class);
    private PoolingHttpClientConnectionManager pcm;

    @Value("${channelUrl}")
    private String channelUrl;

    @Autowired
    public GatewayServiceImpl(PoolingHttpClientConnectionManager pcm){
        this.pcm = pcm;
    }

    @Override
    public HttpResponse getResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String queryString = req.getQueryString() == null ? "" : "?"+req.getQueryString();
        String channelUrlClean = channelUrl.endsWith("/") ? StringUtils.chop(channelUrl) : channelUrl;
        String url = channelUrlClean + req.getRequestURI().replaceAll("/gateway","")+queryString;
        HttpRequestBase httpRequestBase = getImplBaseMethod(req.getMethod(), url);

        if(httpRequestBase instanceof HttpPost){
            ((HttpPost) httpRequestBase).setEntity(new InputStreamEntity(req.getInputStream()));
        }

        log.info("sending request "+httpRequestBase);

        try(CloseableHttpClient httpClient = buildClient()){
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequestBase);
            if(httpResponse.getEntity().getContentType() != null){
                resp.setContentType(httpResponse.getEntity().getContentType().getValue());
            }
            log.info("response from "+httpResponse);
            resp.setStatus(httpResponse.getStatusLine().getStatusCode());
            return httpResponse;
        }catch (Exception e) {
            log.error("Failed to build http client", e);
            return null;
        }
    }

    private void addHeaders(UserProfile userProfile, HttpRequestBase httpRequestBase, HttpServletRequest httpServletRequest){
        httpRequestBase.setHeader("X-STAP-USER", userProfile.getName());
        Enumeration<String> headerNameEnum = httpServletRequest.getHeaderNames();
        while(headerNameEnum.hasMoreElements()){
            String headerName = headerNameEnum.nextElement();
            if(!headerName.matches("Content-Length|Host")){
                httpRequestBase.setHeader(headerName, httpServletRequest.getHeader(headerName));
            }
        }
    }

    private HttpRequestBase getImplBaseMethod(String method, String url){
        return method.equals("GET")
                ? new HttpGet(url) : method.equals("OPTIONS")
                ? new HttpOptions(url) : method.equals("PUT")
                ? new HttpPut(url) : method.equals("DELETE")
                ? new HttpDelete(url) : new HttpPost(url);
    }

    private CloseableHttpClient buildClient(){
        return HttpClientBuilder.create()
                .setConnectionManager(pcm)
                .setConnectionManagerShared(true)
                .build();
    }
}
