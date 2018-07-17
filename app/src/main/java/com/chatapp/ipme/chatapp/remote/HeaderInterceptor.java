package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private SessionManager session;

    public HeaderInterceptor(SessionManager session) {
        this.session = session;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .addHeader("Content-Type", "application/json");

        Object token = SessionManager.getString(SessionKeys.KEY_TOKEN.getKey(), null);

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        Request tokenRequest = requestBuilder.build();

        return chain.proceed(tokenRequest);
    }
}