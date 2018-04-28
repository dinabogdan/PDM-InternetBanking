package org.pdm.ib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ATMCounty {

    @SerializedName("_id")
    private String id;

    @SerializedName("_name")
    private String name;

    @SerializedName("_county")
    private String county;

    private List<ATMLocation> location;

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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public List<ATMLocation> getLocation() {
        return location;
    }

    public void setLocation(List<ATMLocation> location) {
        this.location = location;
    }
}
