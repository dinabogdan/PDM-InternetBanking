package org.pdm.ib.context;

import org.pdm.ib.model.UserCredentials;
import org.pdm.ib.model.UserProfile;

public interface AuthenticationHolder {

    UserCredentials getAuthentication();

    void storeAuthentication(UserCredentials credentials);

    boolean isAuthenticated();

    UserProfile getUserProfile();

    void setUserProfile(UserProfile userProfile);
}
