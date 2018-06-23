package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {

  @SerializedName("id")
  @Expose
  private Integer id;

  @SerializedName("username")
  @Expose
  private String username;

  @SerializedName("createdAt")
  @Expose
  private Date createdAt;

  public User() {
  }

  public User(Integer id, String username, Date createdAt) {
    this.id = id;
    this.username = username;
    this.createdAt = createdAt;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
