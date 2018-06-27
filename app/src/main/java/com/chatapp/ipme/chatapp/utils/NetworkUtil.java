package com.chatapp.ipme.chatapp.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

public class NetworkUtil {
    public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
        return throwable instanceof HttpException
                && ((HttpException) throwable).code() == statusCode;
    }
}

