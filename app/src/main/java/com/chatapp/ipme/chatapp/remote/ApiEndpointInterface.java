package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.model.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndpointInterface {

    @FormUrlEncoded
    @POST("/users/register")
    Observable<User> createUser(@Field("username") String username,
                                @Field("password") String password);

}