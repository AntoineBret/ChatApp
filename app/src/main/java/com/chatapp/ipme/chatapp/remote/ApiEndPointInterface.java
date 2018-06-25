package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.model.Login;
import com.chatapp.ipme.chatapp.model.Message;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.model.User;
import com.google.gson.annotations.JsonAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndPointInterface {

    //User
    @POST("/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<Login> loginUser(@Body HashMap<String, String> map);

    @GET("/users")
    Observable<List<User>> getUsers();
    //todo

    //Messages

    @POST("/messages")
    Observable<Message> createMessage(@Field("id") Integer id,
                                      @Field("content") String content,
                                      @Field("createdAt") Date createdAt);

    @GET("messages")
    Observable<Message> getMessage(@Query("id") Integer id,
                                   @Query("content") String content,
                                   @Query("createdAt") Date createdAt);
    //Room

    @POST("/rooms")
    Observable<Room> createRoom(@Field("name") String name,
                                @Field("users") List users,
                                @Field("messages") List messages);

    @GET("users")
    Observable<Room> getRoom(@Query("id") Integer id,
                             @Query("name") String name,
                             @Query("sizeMax") Integer sizeMax,
                             @Query("language") String language,
                             @Query("users") List users,
                             @Query("messages") List messages);
}
