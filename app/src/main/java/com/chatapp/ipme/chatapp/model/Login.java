package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    public Login(String log, String password) {
    }

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("username")
    @Expose
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"username\": \"" + username + "\",\n" +
                "\t\"password\": \"" + password + "\"\n" +
                "}";
    }
}
