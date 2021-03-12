package com.example.mycinemaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mycinemaapp.adapters.MovieRecyclerView;
import com.example.mycinemaapp.adapters.MovieViewHolder;
import com.example.mycinemaapp.adapters.OnMovieListener;
import com.example.mycinemaapp.adapters.WatchListAdapter;
import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WatchListActivity extends AppCompatActivity implements OnMovieListener {

    private MovieListViewModel movieListViewModel;
    private WatchListAdapter watchListAdapter;
    private RecyclerView  watchList_recyclerView;
    private ImageView back_img;
    private List<MovieModel> watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        initialization();
    }

    private void initialization() {
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        back_img = findViewById(R.id.back_img_watchList);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        watchList = new ArrayList<>();
        watchList_recyclerView = findViewById(R.id.watchListRecycler);

    }
    private void loadWatchList(){

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(movieListViewModel.loadWatchList().subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(movieModel -> {
            //Toast.makeText(getApplicationContext(), "WatchList size is " +movieModel.size(), Toast.LENGTH_SHORT).show();
            if (watchList.size() > 0){
                watchList.clear();
            }
            watchList.addAll(movieModel);
            watchListAdapter = new WatchListAdapter(watchList, this);
            watchList_recyclerView.setAdapter(watchListAdapter);
            watchList_recyclerView.setLayoutManager(new LinearLayoutManager(this));
            watchList_recyclerView.setVisibility(View.VISIBLE);
            compositeDisposable.dispose();
        }) );
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadWatchList();

    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(WatchListActivity.this, MovieDetails.class);
        intent.putExtra("movie", watchListAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onTVShowClicked(MovieModel movieModel) {

    }

    @Override
    public void removeFromWatchList(MovieModel movieModel, int position) {
        CompositeDisposable compositeDisposableForDelete = new CompositeDisposable();
        compositeDisposableForDelete.add(movieListViewModel.removeFromWatchList(movieModel)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( ()-> {
                    watchList.remove(position);
                    watchListAdapter.notifyItemRemoved(position);
                    watchListAdapter.notifyItemRangeChanged(position, watchListAdapter.getItemCount());
                    compositeDisposableForDelete.dispose();
                })
        );
    }
}