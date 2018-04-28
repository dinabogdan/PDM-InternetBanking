package org.pdm.ib.model;

public class UserCredentials {

    private String login;
    private String password;
    private String accessToken;

    public UserCredentials(String login, String password, String accessToken) {
        this.login = login;
        this.password = password;
        this.accessToken = accessToken;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
