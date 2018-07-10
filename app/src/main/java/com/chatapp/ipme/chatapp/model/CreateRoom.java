package com.chatapp.ipme.chatapp.model;

public class CreateRoom {

    public CreateRoom() {
    }

    private String name;
    private String users;

    public String getName() {
        return name;
    }

    public CreateRoom setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsers() {
        return users;
    }

    public CreateRoom setUsers(String users) {
        this.users = users;
        return this;
    }
}
