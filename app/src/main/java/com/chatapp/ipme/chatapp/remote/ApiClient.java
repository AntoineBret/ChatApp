package com.chatapp.ipme.chatapp.remote;

import com.chatapp.ipme.chatapp.model.User;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient instance;
    private ApiEndpointInterface apiEndpointInterface;
    private static String URL = "http://192.168.1.54:8080/";
    private static String token = "";

    private ApiClient() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        apiEndpointInterface = retrofit.create(ApiEndpointInterface.class);
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public Observable<User> createUser(String pseudo, String password) {
        return apiEndpointInterface.createUser(pseudo, password);
    }
}
