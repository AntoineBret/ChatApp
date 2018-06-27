package com.chatapp.ipme.chatapp.remote;

public interface TokenManager {
    String getToken();

    boolean hasToken();

    void clearToken();

    String refreshToken();
}
