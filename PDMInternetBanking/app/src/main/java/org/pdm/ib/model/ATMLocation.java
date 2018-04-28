package org.pdm.ib.model;

public class ATMLocation {

    private String id;
    private String name;
    private String address;
    private String email;
    private ATMGeoLocation map;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ATMGeoLocation getMap() {
        return map;
    }

    public void setMap(ATMGeoLocation map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ATMLocation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", map=" + map +
                '}';
    }
}
