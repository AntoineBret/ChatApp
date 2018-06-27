package com.chatapp.ipme.chatapp.remote;

public class Constants {
    public static final String CONTENT_TYPE = "Content-Type: application/json;charset=UTF-8";
    public static final String AUTHORIZATION = "Authorization: Bearer ";

    public interface httpcodes{
        int STATUS_OK = 200;
        String MESSAGE_STATUS_OK = "Query successfully processed : 200";

//        int STATUS_CREATED = 201;
//        String MESSAGE_STATUS_CREATED = "";

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
    }
}
