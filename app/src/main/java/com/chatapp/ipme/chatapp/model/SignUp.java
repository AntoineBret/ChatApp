package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SignUp {

    public SignUp() {
    }

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("birthdayDate")
    @Expose
    private Date birthdayDate;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("id")
    @Expose
    private String id;


    public SignUp(String password, String username, String firstName, String lastName, Date birthdayDate, String token, String id) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.token = token;
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public SignUp setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SignUp setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SignUp setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SignUp setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public SignUp setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
        return this;
    }

    public String getToken() {
        return token;
    }

    public SignUp setToken(String token) {
        this.token = token;
        return this;
    }

    public String getId() {
        return id;
    }

    public SignUp setId(String id) {
        this.id = id;
        return this;
    }
}