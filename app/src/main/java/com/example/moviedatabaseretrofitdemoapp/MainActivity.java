package com.example.moviedatabaseretrofitdemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.moviedatabaseretrofitdemoapp.model.MovieDatabaseModel;
import com.example.moviedatabaseretrofitdemoapp.network.MDRetrofitInstance;
import com.example.moviedatabaseretrofitdemoapp.network.UrlEndpointClient;

import java.util.List;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<MovieDatabaseModel> mdRepos;
    private RecyclerView recyclerView;
    private MdRetrofitAdaptor mdRetrofitAdaptor;
    public static final String INTENT_MESSAGE = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_list);


        UrlEndpointClient urlEndpointClient = MDRetrofitInstance.getRetrofitInstance().create(UrlEndpointClient.class);

        Call <MovieDatabaseModel> call = urlEndpointClient.getReponse("2ca947b36cea2f898bd68e3a64039720");

        call.enqueue(new Callback<MovieDatabaseModel>() {
            @Override
            public void onResponse(Call<MovieDatabaseModel> call, Response<MovieDatabaseModel> response) {
              MovieDatabaseModel movieDatabaseModel = response.body();

              mdRetrofitAdaptor = new MdRetrofitAdaptor(movieDatabaseModel, new MdRetrofitAdaptor.OnMdRetrofitClickedListener() {
                  @Override
                  public void onItemClicked(MovieDatabaseModel mdRepo, int position) {

                      Intent intent = new Intent(MainActivity.this, GetMovieActivity.class);
                      intent.putExtra(INTENT_MESSAGE, mdRepo.getResults().get(position).getId());
                      startActivity(intent);

                  }
              });

              LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
              recyclerView.setLayoutManager(linearLayoutManager);
              recyclerView.setAdapter(mdRetrofitAdaptor);

            }

            @Override
            public void onFailure(Call<MovieDatabaseModel> call, Throwable t) {

                Log.i("MainActivity", "onFailure: " + t.getMessage());
            }
        });

    }
}
