package com.chatapp.ipme.chatapp.deserializer;

import com.chatapp.ipme.chatapp.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserJsonDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {

        JsonElement jsonUser = je.getAsJsonObject().get("user");
        String token = je.getAsJsonObject().get("token").getAsString();

        User user = new Gson().fromJson(jsonUser, User.class);
        user.setToken(token);

        return user;

    }
}