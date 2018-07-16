package com.chatapp.ipme.chatapp.remote;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chatapp.ipme.chatapp.api.BaseUrl.BASE_URL;

public class ApiClient {

    private static Retrofit retrofit = null;
    private Context context = null;

    public ApiClient(Context context) {
        setContext(context);
    }

    public ApiClient() {
    }

    public Retrofit getClient() {

        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = builder
                    .addInterceptor(interceptor)
                    .addInterceptor(new HeaderIntercepter(new SessionManager(context)))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public Context getContext() {
        return context;
    }

    public ApiClient setContext(Context context) {
        this.context = context;
        return this;
    }
}



