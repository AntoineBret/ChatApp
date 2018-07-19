package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserResponse <T> {

  @SerializedName("user")
  @Expose
  private User user;

  @SerializedName("token")
  @Expose
  private String token;

  public UserResponse(Integer ID, String username, String password, String firstname, String lastname, String birthday, String email) {
    user.getID();
    user.getUsername();
    user.getPassword();
    user.getFirstname();
    user.getLastname();
    user.getBirthday();
    user.getEmail();
    this.user = user;
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
