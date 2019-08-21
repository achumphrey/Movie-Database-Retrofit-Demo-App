package com.example.moviedatabaseretrofitdemoapp.network;

import com.example.moviedatabaseretrofitdemoapp.model.DetailModel;
import com.example.moviedatabaseretrofitdemoapp.model.MovieDatabaseModel;
import com.example.moviedatabaseretrofitdemoapp.model.MovieDatabaseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UrlEndpointClient {

        @GET("movie/popular")
        Call<MovieDatabaseModel> getReponse(@Query("api_key") String api_key);

//        @GET("movie/{movie_id}?api_key=2ca947b36cea2f898bd68e3a64039720")
//        Call<DetailModel> getMvIdReponse(@Path("movie_id") int movie_id);

        @GET("movie/{movie_id}")
        Call<DetailModel> getMvIdReponse(@Path("movie_id") int movie_id,
                                         @Query("api_key") String apiKey);

}
