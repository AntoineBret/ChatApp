package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.model.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ApiEndpointInterface {

    @POST("/users")
    Observable<User> createUser(@Field("password") String password,
                                @Field("pseudo") String pseudo);

}