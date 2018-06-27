package com.chatapp.ipme.chatapp.remote;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*public class TokenInterceptor implements Interceptor {
    private final TokenManager tokenManager;

    TokenInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Request intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request modifiedRequest = request;
        if (tokenManager.hasToken()) {
            modifiedRequest = request.newBuilder()
                    .addHeader("token", tokenManager.getToken())
                    .build();
        }
        return request;
    }

}*/