package org.pdm.ib.context;

import org.pdm.ib.model.UserCredentials;

public interface AuthenticationHolder {

    UserCredentials getAuthentication();

    void storeAuthentication(UserCredentials credentials);

    boolean isAuthenticated();
}
