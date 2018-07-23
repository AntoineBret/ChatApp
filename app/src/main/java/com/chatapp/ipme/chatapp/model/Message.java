package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    public Message() {
    }

    @SerializedName("id")
    @Expose
    private Room roomID;

    @SerializedName("content")
    @Expose
    private String messageContent;

    @SerializedName("user")
    @Expose
    private User user;

    public Message(Room roomID, String messageContent, User user) {
        this.roomID = roomID;
        this.messageContent = messageContent;
        this.user = user;
    }

    public Room getRoomID() {
        return roomID;
    }

    public Message setRoomID(Room roomID) {
        this.roomID = roomID;
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

    @Override
    public String toString() {
        return "Message{" +
                "roomID=" + roomID +
                ", messageContent='" + messageContent + '\'' +
                ", user=" + user +
                '}';
    }
}







