package com.xieziming.stap.core.security.auth;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by Suny on 8/13/16.
 */
@Configuration
public class AuthCache {
    private static final Logger log = LoggerFactory.getLogger(AuthCache.class);
    @Bean
    public Cache<String, AuthResult> userCache() {
        Cache<String, AuthResult> userCache = CacheBuilder.newBuilder()
                .maximumSize(10000L)
                .expireAfterAccess(60, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<String, AuthResult>() {
                    public void onRemoval(RemovalNotification<String, AuthResult> notification) {
                        log.info("User " + notification.getValue().getUserDto().getName() + "'s authorization has expired.");
                    }
                })
                .build();
        return userCache;
    }
}
