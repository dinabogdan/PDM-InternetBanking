package org.pdm.ib.service.impl;

import org.pdm.ib.context.AuthenticationHolder;
import org.pdm.ib.auth.AuthenticationHolderConfig;
import org.pdm.ib.model.UserCredentials;
import org.pdm.ib.service.AuthService;
import org.pdm.ib.util.ThreadUtil;

import java.util.Arrays;
import java.util.List;

public class AuthServiceImpl implements AuthService {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final List<UserCredentials> USERS_DUMMY_CREDENTIALS = Arrays.asList(
        new UserCredentials("alex.chihaia", "1234", "DUMMY_TOKEN_1"),
        new UserCredentials("bogdan.dina", "1234", "DUMMY_TOKEN_2"),
        new UserCredentials("sebi.burchi", "1234", "DUMMY_TOKEN_3")
    );

    private static AuthenticationHolder authenticationHolder = AuthenticationHolderConfig.getAuthenticationHolder();

    @Override
    public boolean login(String username, String password) {
        if (authenticationHolder.isAuthenticated()) {
            return true;
        }

        // Simulate network access.
        ThreadUtil.sleep(1000L);

        for (UserCredentials credentials : USERS_DUMMY_CREDENTIALS) {
            if (username.equals(credentials.getLogin())) {
                if (password.equals(credentials.getPassword())) {
                    authenticationHolder.storeAuthentication(credentials);

                    return true;
                }
            }
        }

        return false;
    }
}
