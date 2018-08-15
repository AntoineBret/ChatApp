package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.model.Message;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.model.Thumbnail;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.model.UserResponse;
import com.chatapp.ipme.chatapp.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    //Modify currently logged user data
    //todo : make dynamic endpoint
    @PUT("/api/users/{user_ID}")
    Observable<Response<User>> editUser(@Body Map<String, Object> map, @Path("user_ID")Integer user_ID);

    //Delete currently logged user account
    @DELETE("/api/users/{id}")
    Observable<User> deletUser();

    //create rooms with one user
    @POST("/api/rooms")
    Observable<Response<Room>> createNewRoom(@Body HashMap<String, Object> map);

    //get all rooms of connected user
    @GET("/api/user/rooms")
    Observable<Response<List<Room>>> getRooms();

    //send message
    @POST("/api/messages")
    Observable<Response<Message>> sendMessages(@Body HashMap<String, Object> map);

    //get message of room_ID with dynamic path
    @GET("/api/rooms/{room_ID}/messages")
    Observable<Response<List<Message>>> getRoomMessages(@Path("room_ID")Integer room_ID);

    //post thumbnails
    @POST("/public/upload")
    Observable<Response<List<Thumbnail>>> sendThumbnail();

    //get thumbnails
    @GET("/public/thumbnails/{filename}")
    Observable<Response<List<Thumbnail>>> getThumbnail();
}
