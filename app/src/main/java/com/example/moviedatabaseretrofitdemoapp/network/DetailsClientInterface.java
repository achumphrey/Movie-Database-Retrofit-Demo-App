package com.example.moviedatabaseretrofitdemoapp.network;

import com.example.moviedatabaseretrofitdemoapp.model.MovieDatabaseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailsClientInterface {

    @GET("movie/{movieId}2ca947b36cea2f898bd68e3a64039720")
    Call<MovieDatabaseModel> getReponse(@Query("movieId") String api_key);
}
