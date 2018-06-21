package com.chatapp.ipme.chatapp.model;

public class Room {

    private Integer id;
    private String language;
    private String name;
    private Integer size;

    public Room() {
    }

    public Room(Integer id, String language, String name, Integer size) {
        this.id = id;
        this.language = language;
        this.name = name;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
