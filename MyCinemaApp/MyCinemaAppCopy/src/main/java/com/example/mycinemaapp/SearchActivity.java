package com.example.mycinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mycinemaapp.adapters.MovieRecyclerView;
import com.example.mycinemaapp.adapters.OnMovieListener;
import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements OnMovieListener {

    private ImageView search_cinema;
    private ImageView back_img;
    private EditText input_search;

    private MovieListViewModel movieListViewModel;
    private RecyclerView recyclerView;
    private MovieRecyclerView adapterRecyclerView;

    private List<MovieModel> list = new ArrayList<>();
    private List<String> stringsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initialization();
        observeAnyChange();
        
    }

    private void initialization(){
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        input_search = findViewById(R.id.input_search);

        back_img = findViewById(R.id.back_img);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

        search_cinema = findViewById(R.id.search_image);
        search_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupSearchView();
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        //Настраиваем RecyclerView
        recyclerView = findViewById(R.id.searchRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapterRecyclerView =  new MovieRecyclerView( list,this); // проверить
        recyclerView.setAdapter(adapterRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Загрузка следующей страницы поиска
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1)){
                    movieListViewModel.searchNextPage();
                }
            }
        });
    }

    // Следим за любыми изменениями в date
    private void observeAnyChange(){
        movieListViewModel.getmMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
           public void onChanged(List<MovieModel> list_movie_model ) {
                if (list_movie_model != null){
                    for (MovieModel movieModel: list_movie_model){
                        Log.v("Tag", "on" +movieModel.getName());
                            adapterRecyclerView.setmMovies(list_movie_model);
                            stringsList.add(movieModel.getName());

                    }
                }
            }
        });
    }

    // Получаем data с поиска
    private void setupSearchView() {
        String user_search = null;
        if (input_search != null) {
            user_search = input_search.getText().toString();
        }
        movieListViewModel.searchMovieApi(user_search, 1);
        Log.v("List","msg/// " +list);

     }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(SearchActivity.this, MovieDetails.class);
        intent.putExtra("movie", adapterRecyclerView.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onTVShowClicked(MovieModel movieModel) {

    }

    @Override
    public void removeFromWatchList(MovieModel movieModel, int position) {

    }
}