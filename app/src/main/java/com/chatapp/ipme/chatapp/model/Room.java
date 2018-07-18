package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Room<T> {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("sizeMax")
    @Expose
    private Integer sizeMax;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("users")
    @Expose
    private List<User> users;

    private String interlocutorName;


    public Room(Integer id, String name, Integer sizeMax, String language, List<User> users, String interlocutorName) {
        this.id = id;
        this.name = name;
        this.sizeMax = sizeMax;
        this.language = language;
        this.users = users;
        this.interlocutorName = interlocutorName;
    }

    public Integer getId() {
        return id;
    }

    public Room<T> setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room<T> setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSizeMax() {
        return sizeMax;
    }

    public Room<T> setSizeMax(Integer sizeMax) {
        this.sizeMax = sizeMax;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Room<T> setLanguage(String language) {
        this.language = language;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Room<T> setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public String getInterlocutorName() {
        return interlocutorName;
    }

    public Room<T> setInterlocutorName(String interlocutorName) {
        this.interlocutorName = interlocutorName;
        return this;
    }
}
