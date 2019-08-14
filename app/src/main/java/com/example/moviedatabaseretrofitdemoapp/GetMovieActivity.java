package com.example.moviedatabaseretrofitdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.moviedatabaseretrofitdemoapp.model.DetailModel;
import com.example.moviedatabaseretrofitdemoapp.model.MovieDatabaseModel;
import com.example.moviedatabaseretrofitdemoapp.network.DetailInstance;
import com.example.moviedatabaseretrofitdemoapp.network.DetailsClientInterface;
import com.example.moviedatabaseretrofitdemoapp.network.MDRetrofitInstance;
import com.example.moviedatabaseretrofitdemoapp.network.UrlEndpointClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_overview;
    private ImageView img_view;
    private RatingBar rt_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_movie);

        tv_title = findViewById(R.id.tv_title);
        tv_overview = findViewById(R.id.tv_overview);
        img_view = findViewById(R.id.img_view);
        rt_bar = findViewById(R.id.rt_bar);

        Intent intent = getIntent();
        int movie_id = intent.getIntExtra(MainActivity.INTENT_MESSAGE, 0);

        UrlEndpointClient urlEndpointClient = MDRetrofitInstance.getRetrofitInstance().create(UrlEndpointClient.class);
        Call <DetailModel> call = urlEndpointClient.getMvIdReponse(movie_id);

        call.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {

                DetailModel detailModel = response.body();

                tv_title.setText(detailModel.getTitle());
                tv_overview.setText(detailModel.getOverview());
                Picasso.get().load("https://image.tmdb.org/t/p/w185"+ detailModel.getPosterPath())
                        .into(img_view);
                rt_bar.setRating(detailModel.getVoteAverage().floatValue());
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {

            }
        });

    }
}