package com.yan.paging.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private Retrofit retrofit;
    private final String BASE_URL = "";

    private RetrofitClient(){
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder().build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(instance==null)
            instance = new RetrofitClient();
        return instance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
