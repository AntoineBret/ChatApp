package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

  public User() {
  }

  @SerializedName("id")
  @Expose
  private Integer ID;

  @SerializedName("username")
  @Expose
  private String username;

  @SerializedName("password")
  @Expose
  private String password;

  @SerializedName("firstName")
  @Expose
  private String firstname;

  @SerializedName("lastName")
  @Expose
  private String lastname;

  @SerializedName("birthdayDate")
  @Expose
  private String birthday;

  @SerializedName("email")
  @Expose
  private String email;

  public User(Integer ID, String username, String password, String firstname, String lastname, String birthday, String email) {
    this.ID = ID;
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthday = birthday;
    this.email = email;
  }

  public Integer getID() {
    return ID;
  }

  public void setID(Integer ID) {
    this.ID = ID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getBirthday() {
    return birthday;
  }

  public User setBirthday(String birthday) {
    this.birthday = birthday;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
