package org.pdm.ib.service.impl;

import org.pdm.ib.auth.AuthenticationHolderConfig;
import org.pdm.ib.command.UserAuthCommand;
import org.pdm.ib.context.AuthenticationHolder;
import org.pdm.ib.login.LoginActivity;
import org.pdm.ib.model.UserCredentials;
import org.pdm.ib.model.UserProfile;
import org.pdm.ib.retrofit.RetrofitAPIService;
import org.pdm.ib.service.AuthService;
import org.pdm.ib.util.ThreadUtil;

public class AuthServiceImpl implements AuthService {

    private static AuthenticationHolder authenticationHolder = AuthenticationHolderConfig.getAuthenticationHolder();
    private RetrofitAPIService retrofitAPIService = RetrofitAPIService.aRetrofitApiService();
    private UserProfileServiceImpl userProfileService = UserProfileServiceImpl.anUserProfileInstance();

    @Override
    public boolean login(String username, String password) {
        if (authenticationHolder.isAuthenticated()) {
            return true;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserAuthCommand userAuthCommand = new UserAuthCommand
                        .Builder()
                        .withUsername(username)
                        .withPassword(password)
                        .build();
                UserProfile userProfile = retrofitAPIService.authorizeUser(userAuthCommand);
                if (userProfile != null) {
                    UserCredentials userCredentials = new UserCredentials(username, password, userProfile.getAccessToken());
                    authenticationHolder.storeAuthentication(userCredentials);
                    authenticationHolder.setUserProfile(userProfile);
                }
            }
        }).start();
        return authenticationHolder.getAuthentication() == null;
    }
}
