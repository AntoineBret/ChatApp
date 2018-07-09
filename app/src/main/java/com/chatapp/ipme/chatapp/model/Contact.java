package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    public Contact() {
    }

    @SerializedName("username")
    @Expose
    private String username;

    public Contact(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
