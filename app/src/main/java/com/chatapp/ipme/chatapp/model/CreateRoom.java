package com.chatapp.ipme.chatapp.model;

public class CreateRoom {

    public CreateRoom() {
    }

    private String name;
    private String users;
    private Integer id;

    public CreateRoom(String name, String users, Integer id) {
        this.name = name;
        this.users = users;
        this.id = id;
    }

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

    public Integer getId() {
        return id;
    }

    public CreateRoom setId(Integer id) {
        this.id = id;
        return this;
    }
}
