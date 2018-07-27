package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {

    public Message() {
    }

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("id")
    @Expose
    private Integer messageID;

    @SerializedName("content")
    @Expose
    private String messageContent;

    @SerializedName("createdAt")
    @Expose
    private Long createdAt;

    @SerializedName("room")
    @Expose
    private Room room;

    public Message(Integer ID, String username, String password, String firstname, String lastname, String birthday, String email) {
        user.getID();
        user.getUsername();
        user.getPassword();
        user.getFirstname();
        user.getLastname();
        user.getBirthday();
        user.getEmail();
        this.createdAt = createdAt;
        this.messageID = messageID;
        this.messageContent = messageContent;
        this.room = room;
    }

    public Integer getMessageID() {
        return messageID;
    }

    public Message setMessageID(Integer messageID) {
        this.messageID = messageID;
        return this;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Message setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Message setUser(User user) {
        this.user = user;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Message setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Message setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}







