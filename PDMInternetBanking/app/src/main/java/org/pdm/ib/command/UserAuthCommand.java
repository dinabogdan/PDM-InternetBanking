package org.pdm.ib.command;

import com.google.gson.annotations.SerializedName;

public class UserAuthCommand {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("referenceId")
    private Integer referenceId;
    @SerializedName("isAuthorized")
    private Boolean isAuthorized;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("accessToken")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public Boolean getAuthorized() {
        return isAuthorized;
    }

    public UserAuthCommand(String username, String password, Integer referenceId, Boolean isAuthorized) {
        this.username = username;
        this.password = password;
        this.referenceId = referenceId;
        this.isAuthorized = isAuthorized;
    }

    public UserAuthCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }


    private UserAuthCommand(Builder builder) {
        username = builder.username;
        password = builder.password;
        referenceId = builder.referenceId;
        isAuthorized = builder.isAuthorized;
        lastName = builder.lastName;
        firstName = builder.firstName;
        accessToken = builder.accessToken;
    }

    public static final class Builder {
        private String username;
        private String password;
        private Integer referenceId;
        private Boolean isAuthorized;
        private String lastName;
        private String firstName;
        private String accessToken;

        public Builder() {
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withReferenceId(Integer val) {
            referenceId = val;
            return this;
        }

        public Builder withIsAuthorized(Boolean val) {
            isAuthorized = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withAccessToken(String val) {
            accessToken = val;
            return this;
        }

        public UserAuthCommand build() {
            return new UserAuthCommand(this);
        }
    }
}
