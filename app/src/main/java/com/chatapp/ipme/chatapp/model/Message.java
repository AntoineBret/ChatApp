package com.chatapp.ipme.chatapp.model;

import org.json.JSONObject;

import java.util.List;

public class Message {

    private Integer id;
    private String pseudo;
    private List<Object> messages = null;
    private Integer createdAt;

    public static Message parse(JSONObject object) {
        Message message = new Message();
        message.id =  object.optInt("id");
        message.pseudo = object.optString("pseudo");
        message.messages = (List<Object>) object.optJSONObject("messages");
        message.createdAt = object.optInt("createdAt");
        return message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
}







