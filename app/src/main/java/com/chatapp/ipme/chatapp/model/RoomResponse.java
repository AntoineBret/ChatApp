package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomResponse<T> {

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


    public RoomResponse(Integer id, String name, Integer sizeMax, String language, List<User> users, String interlocutorName) {
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

    public RoomResponse<T> setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoomResponse<T> setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSizeMax() {
        return sizeMax;
    }

    public RoomResponse<T> setSizeMax(Integer sizeMax) {
        this.sizeMax = sizeMax;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public RoomResponse<T> setLanguage(String language) {
        this.language = language;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public RoomResponse<T> setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public String getInterlocutorName() {
        return interlocutorName;
    }

    public RoomResponse<T> setInterlocutorName(String interlocutorName) {
        this.interlocutorName = interlocutorName;
        return this;
    }
}
