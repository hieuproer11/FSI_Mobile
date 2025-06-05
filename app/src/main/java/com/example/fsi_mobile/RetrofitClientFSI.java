package com.example.fsi_mobile;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClientFSI {
    private static RetrofitClientFSI instance = null;
    private ApiFSI myApi;

    private RetrofitClientFSI(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiFSI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiFSI.class);
    }

    public static RetrofitClientFSI getInstance(){
        if(instance == null) {
            instance = new RetrofitClientFSI();
        }
        return instance;
    }

    public ApiFSI getMyApi(){
        return myApi;
    }
}
