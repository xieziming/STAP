package com.xieziming.stap.gateway.service;

import com.xieziming.stap.gateway.mode.UserProfile;

/**
 * Created by Suny on 7/6/16.
 */
public interface UserProfileService {
    UserProfile getUserProfile(String principal);
}
