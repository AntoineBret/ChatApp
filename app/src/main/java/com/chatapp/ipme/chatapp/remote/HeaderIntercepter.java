package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderIntercepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String token = SessionManager.KEY_TOKEN;

        Request.Builder requestBuilder = request.newBuilder()
                .addHeader("Content-Type", "application/json");

        if (null != token) {
            requestBuilder.addHeader("Authorization", "Bearer " + SessionManager.KEY_TOKEN);
        }

        Request tokenRequest = requestBuilder.build();

        return chain.proceed(tokenRequest);
    }
}