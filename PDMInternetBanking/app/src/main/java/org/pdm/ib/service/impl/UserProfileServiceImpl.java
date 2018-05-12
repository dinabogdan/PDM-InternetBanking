package org.pdm.ib.service.impl;

import org.pdm.ib.context.AuthenticationHolder;
import org.pdm.ib.auth.AuthenticationHolderConfig;
import org.pdm.ib.model.UserProfile;
import org.pdm.ib.service.UserProfileService;

import java.util.HashMap;
import java.util.Map;

public class UserProfileServiceImpl implements UserProfileService {

    private AuthenticationHolder authenticationHolder = AuthenticationHolderConfig.getAuthenticationHolder();

    @Override
    public UserProfile getProfile() {
        if (authenticationHolder.getAuthentication() != null) {
            return authenticationHolder.getUserProfile();
        }
        //ignore null check
        return null;
    }

    public static UserProfileServiceImpl anUserProfileInstance() {
        return new UserProfileServiceImpl();
    }
}
