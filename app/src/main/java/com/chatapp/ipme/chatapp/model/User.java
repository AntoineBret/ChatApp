package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    public User() {
    }

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("id")
    @Expose
    private String ID;

    public User(String password, String username, String token, String ID) {
        this.password = password;
        this.username = username;
        this.token = token;
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public String getID() {
        return ID;
    }

    public User setID(String ID) {
        this.ID = ID;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
