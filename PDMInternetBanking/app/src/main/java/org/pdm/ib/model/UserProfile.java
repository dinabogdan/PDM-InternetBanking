package org.pdm.ib.model;

public class UserProfile {

    private String firstName;
    private String lastName;
    private Integer referenceId;
    private String accessToken;

    public UserProfile() {

    }

    public UserProfile(String firstName, String lastName, Integer referenceId, String accessToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.referenceId = referenceId;
        this.accessToken = accessToken;
    }

    public UserProfile(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
