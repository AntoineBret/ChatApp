package com.chatapp.ipme.chatapp.model;

public class DisplayRoom {

    private String name;

  public DisplayRoom() {
  }

  public DisplayRoom(String name, String subname, int thumbnail) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
