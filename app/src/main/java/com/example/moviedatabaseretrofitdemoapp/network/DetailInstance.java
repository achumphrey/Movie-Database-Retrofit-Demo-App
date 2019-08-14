package com.example.moviedatabaseretrofitdemoapp.network;

import com.example.moviedatabaseretrofitdemoapp.BaseUrlConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailInstance {

    private static Retrofit dRetrofit;

    public static Retrofit getRetrofitInstance(){
        if (dRetrofit == null){
            dRetrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrlConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return dRetrofit;
    }
}
