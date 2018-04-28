package org.pdm.ib.auth;

import org.pdm.ib.context.AuthenticationHolder;
import org.pdm.ib.model.UserCredentials;

public class InMemoryAuthenticationHolder implements AuthenticationHolder {

    private UserCredentials credentials;

    /**
     * Package private - use {@link AuthenticationHolderConfig#getAuthenticationHolder()}
     */
    InMemoryAuthenticationHolder() {

    }

    @Override
    public UserCredentials getAuthentication() {
        return credentials;
    }

    @Override
    public void storeAuthentication(UserCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public boolean isAuthenticated() {
        return credentials != null;
    }
}
