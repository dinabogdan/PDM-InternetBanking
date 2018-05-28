package org.pdm.ib.model;


import com.google.gson.annotations.SerializedName;

public class Notification {

    private int id;
    @SerializedName("notificationMessage")
    private String message;

    public Notification() {
    }

    public Notification(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}