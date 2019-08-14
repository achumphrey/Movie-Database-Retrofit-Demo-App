package com.example.moviedatabaseretrofitdemoapp.network;

import com.example.moviedatabaseretrofitdemoapp.BaseUrlConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MDRetrofitInstance {

    private static Retrofit mdRetrofit;

    public static Retrofit getRetrofitInstance(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        if (mdRetrofit == null){
            mdRetrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrlConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mdRetrofit;
    }
}
