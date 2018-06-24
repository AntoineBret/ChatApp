package com.chatapp.ipme.chatapp.model;

public class Room {

    private String name;
    private String subname;
    private int thumbnail; //todo : add to back end

  public Room() {
  }

  public Room(String name, String subname, int thumbnail) {
    this.name = name;
    this.subname = subname;
    this.thumbnail = thumbnail;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSubname() {
    return subname;
  }

  public void setSubname(String subname) {
    this.subname = subname;
  }

  public int getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(int thumbnail) {
    this.thumbnail = thumbnail;
  }
}
