package com.chatapp.ipme.chatapp.remote;

import java.io.IOException;
import java.util.HashMap;

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

        HashMap<String, Object> user = session.getUserDetails();
        Object token = user.get(SessionManager.KEY_TOKEN);

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        Request tokenRequest = requestBuilder.build();

        return chain.proceed(tokenRequest);
    }
}
