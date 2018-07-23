package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.model.Message;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.model.UserResponse;
import com.chatapp.ipme.chatapp.utils.Constants;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndPointInterface {

    //log to service
    @POST("/login")
    @Headers({Constants.CONTENT_TYPE})
    Observable<Response<UserResponse>> loginUser(@Body HashMap<String, String> map);

    //register to service
    @POST("/register")
    @Headers({Constants.CONTENT_TYPE})
    Observable<Response<UserResponse>> signupUser(@Body HashMap<String, String> map);

    //get all users register on service
    @GET("/api/users/")
    Observable<Response<List<User>>> getContacts();

    //create rooms with one user
    @POST("/api/rooms")
    Observable<Room> createNewRoom(@Body HashMap<String, Object> map);

    //get all rooms of connected user
    @GET("/api/user/rooms")
    Observable<Response<List<Room>>> getRooms();

    //send message
    @POST("/api/messages")
    Observable<Response<Message>> sendMessages(@Body HashMap<String, String> map);

    //get message
    @GET("/api/messages")
    Observable<Response<Message>> getMessages();

}
