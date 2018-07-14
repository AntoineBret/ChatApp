package com.chatapp.ipme.chatapp.model;

public class Room {

    public Room() {
    }

    private String name;
    private String users;
    private Integer id;

    public Room(String name, String users, Integer id) {
        this.name = name;
        this.users = users;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsers() {
        return users;
    }

    public Room setUsers(String users) {
        this.users = users;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Room setId(Integer id) {
        this.id = id;
        return this;
    }
}
