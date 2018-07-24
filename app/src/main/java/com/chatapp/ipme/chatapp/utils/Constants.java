package com.chatapp.ipme.chatapp.utils;

import static com.chatapp.ipme.chatapp.api.BaseUrl.BASE_URL;

public class Constants {

    //Set content_type for json request header
    public static final String CONTENT_TYPE = "Content-Type: application/json;charset=UTF-8";

    //Set individual room size
    public static final Integer INDIVIDUAL_ROOM_SIZE = 2;

    //Set view type for RoomDetailsAdapter
    public static final int VIEW_TYPE_MESSAGE_SENT = 1;
    public static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    //Http codes list
    public interface httpcodes{
        int STATUS_OK = 200;
        String MESSAGE_STATUS_OK = "Query successfully processed : 200";

        int STATUS_NO_CONTENT= 204;
        String MESSAGE_NO_CONTENT = "";

        int STATUS_BAD_REQUEST = 400;
        String MESSAGE_BAD_REQUEST = "Bad request : 404 error";

        int STATUS_UNAUTHORIZED = 401;
        String MESSAGE_UNAUTHORIZED = "Unauthenticated user : 401 error";

        int STATUS_FORBITTEN = 403;
        String MESSAGE_FORBITTEN = "Access denied : 403 error";

        int STATUS_NOT_FOUND = 404;
        String MESSAGE_NOT_FOUND = " Page not found : 404 error";

        int STATUS_SERVER_ERROR = 500;
        String MESSAGE_SERVER_ERROR = "Server Error : 500 error";

        String ERROR_UNKNOWN = "Error not hold : unknown error";

        String BAD_CREDENTIALS = "Identifiant ou mot de passe incorrect";

        String MESSAGE_CONNECT_EXCEPTION = "Failed to connect to " + BASE_URL;
    }
}
