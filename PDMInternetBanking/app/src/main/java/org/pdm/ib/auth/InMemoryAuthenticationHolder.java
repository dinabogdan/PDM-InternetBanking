package org.pdm.ib.auth;

import org.pdm.ib.context.AuthenticationHolder;
import org.pdm.ib.model.UserCredentials;
import org.pdm.ib.model.UserProfile;

public class InMemoryAuthenticationHolder implements AuthenticationHolder {

    private UserCredentials credentials;
    private UserProfile userProfile;

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

    @Override
    public UserProfile getUserProfile() {
        return userProfile;
    }

    @Override
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
