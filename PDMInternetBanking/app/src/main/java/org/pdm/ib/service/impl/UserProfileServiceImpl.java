package org.pdm.ib.service.impl;

import org.pdm.ib.context.AuthenticationHolder;
import org.pdm.ib.auth.AuthenticationHolderConfig;
import org.pdm.ib.model.UserProfile;
import org.pdm.ib.service.UserProfileService;

import java.util.HashMap;
import java.util.Map;

public class UserProfileServiceImpl implements UserProfileService {

    private AuthenticationHolder authenticationHolder = AuthenticationHolderConfig.getAuthenticationHolder();

    private static Map<String, UserProfile> userProfiles = new HashMap<>();

    static {
        userProfiles.put("DUMMY_TOKEN_1", new UserProfile("Alex", "Chihaia"));
        userProfiles.put("DUMMY_TOKEN_2", new UserProfile("Bogdan", "Dina"));
        userProfiles.put("DUMMY_TOKEN_3", new UserProfile("Sebi", "Burchi"));
    }

    @Override
    public UserProfile getProfile() {
//        String accessToken = authenticationHolder.getAuthentication().getAccessToken();
        String accessToken = "DUMMY_TOKEN_1";

        //ignore null check
        return userProfiles.get(accessToken);
    }
}
