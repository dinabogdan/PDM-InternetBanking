package org.pdm.ib.auth;

import org.pdm.ib.context.AuthenticationHolder;

public class AuthenticationHolderConfig {

    private static AuthenticationHolder authenticationHolder;

    public static AuthenticationHolder getAuthenticationHolder() {
        if (authenticationHolder == null) {
            authenticationHolder = new InMemoryAuthenticationHolder();
        }

        return authenticationHolder;
    }
}
