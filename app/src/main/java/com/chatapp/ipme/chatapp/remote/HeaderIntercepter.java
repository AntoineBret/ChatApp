package com.chatapp.ipme.chatapp.remote;

import android.util.Log;

import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderIntercepter implements Interceptor {

    private SessionManager session;

    public HeaderIntercepter(SessionManager session) {
        this.session = session;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .addHeader("Content-Type", "application/json");

        Map<String, String> user = session.getUserDetails();
        String token = user.get(SessionManager.KEY_TOKEN);

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        Request tokenRequest = requestBuilder.build();

        return chain.proceed(tokenRequest);
    }
}