package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.utils.Constants;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.model.UserResponse;

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
    Observable<List<User>> getContacts();

    //get all rooms of connected user
    @GET("/api/user/rooms")
    Observable<List<Room>> getRooms();

    //create rooms with one user
    @POST("/api/rooms")
    Observable<Room> createNewRoom(@Body HashMap<String, String> map);

}
