package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.api.Constants;
import com.chatapp.ipme.chatapp.model.Contact;
import com.chatapp.ipme.chatapp.model.CreateRoom;
import com.chatapp.ipme.chatapp.model.Login;
import com.chatapp.ipme.chatapp.model.DisplayRoom;
import com.chatapp.ipme.chatapp.model.SignUp;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndPointInterface {

    //log to service
    @POST("/login")
    @Headers({Constants.CONTENT_TYPE})
    Observable<Login> loginUser(@Body HashMap<String, String> map);

    //register to service
    @POST("/register")
    @Headers({Constants.CONTENT_TYPE})
    Observable<SignUp> signupUser(@Body HashMap<String, String> map);

    //get all users register on service
    @GET("/api/users/")
    Observable<List<Contact>> getContacts();

    //get all rooms of connected user
    @GET("/api/user/rooms")
    Observable<List<DisplayRoom>> getRooms();

    //create rooms with one user
    @POST("/api/rooms")
    Observable<CreateRoom> createNewRoom(@Body HashMap<String, String> map);

}
