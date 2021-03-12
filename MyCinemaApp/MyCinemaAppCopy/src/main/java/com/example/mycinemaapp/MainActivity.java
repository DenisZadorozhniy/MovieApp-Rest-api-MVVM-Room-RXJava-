package com.example.mycinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.content.res.Resources;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mycinemaapp.adapters.MovieRecyclerView;
import com.example.mycinemaapp.adapters.OnMovieListener;
import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.models.TVShowDetails;
import com.example.mycinemaapp.repositories.MovieDetailsRepository;
import com.example.mycinemaapp.request.Servicey;
import com.example.mycinemaapp.response.MostPopularResponse;
import com.example.mycinemaapp.response.MovieDetailsResponse;
import com.example.mycinemaapp.response.MovieSearchResponse;
import com.example.mycinemaapp.utils.MovieApi;
import com.example.mycinemaapp.viewmodels.MovieListViewModel;
import com.google.android.material.theme.MaterialComponentsViewInflater;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnMovieListener {

    private RelativeLayout relativeLayout_main;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private MovieListViewModel movieListViewModel;
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;
    private ImageView search_img, watch_list_img;
    private List<MovieModel> tvShowList = new ArrayList<>();
    private List<TVShowDetails> details = new ArrayList<>();

    private int page = 1;
    private int totalPages = 1;
    private String query = "29560";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BlackTheme);
        setContentView(R.layout.activity_main);



        initialization();
        // getRetrofitReponse();
        observeAnyChange();
        //searchMovieApi("Arrow",1); // проверка работы api
        mostPopularApi(1);

        Animation animRotateIn_icon = AnimationUtils.loadAnimation(this,
                R.anim.bottom_in);

        relativeLayout_main.startAnimation(animRotateIn_icon);

      /*  if(recyclerView.){
            progressBar.setVisibility(View.GONE);
        } */

    }

    private void initialization(){
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        relativeLayout_main =  findViewById(R.id.main_layout);
        progressBar = findViewById(R.id.pb_horizontal);
        progressBar.setVisibility(View.VISIBLE);

        toolbar = findViewById(R.id.layout_header);
        search_img = findViewById(R.id.icon_search);
        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent_search);
            }
        });
        watch_list_img =  findViewById(R.id.watchList);
        watch_list_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_watchList = new Intent(MainActivity.this, WatchListActivity.class);
                startActivity(intent_watchList);
            }
        });

        //Настраиваем RecyclerView
        recyclerView = findViewById(R.id.tvShowsRecyclerView);
        movieRecyclerAdapter = new MovieRecyclerView(tvShowList,this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Загрузка следующей страницы поиска
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                movieListViewModel.searchNextPageMostPopular();
            }
        });
    }

    // Следим за любыми изменениями в date
    private void observeAnyChange(){
        movieListViewModel.getMostPopularMovies(page).observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null){
                    for (MovieModel movieModel: movieModels){
                        //     Log.v("Tag", "on" +movieModel.getName());
                        movieRecyclerAdapter.setmMovies(movieModels);
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
    // 4 - Calling method in MainActivity (MostPopular)
    private void mostPopularApi(int page){
        movieListViewModel.mostPopularApi(page);
    }

    private void getRetrofitReponse() {
        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi
                .searchMovie(
                        "arrow",
                        1
                );
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){
                    List<MovieModel> movies = new ArrayList<>(response.body().getList_movies());
                    for(MovieModel movie: movies){
                        Log.v("Tag","The list" +movie.getName());
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMovieClick(int position) {

        Intent intent = new Intent(MainActivity.this, MovieDetails.class);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onTVShowClicked(MovieModel movieModel) {

    }

    @Override
    public void removeFromWatchList(MovieModel movieModel, int position) {

    }
}