package com.example.mycinemaapp.adapters;

import com.example.mycinemaapp.models.MovieModel;

public interface OnMovieListener {
    void onMovieClick(int position);

    void onTVShowClicked(MovieModel movieModel);
    void removeFromWatchList(MovieModel movieModel, int position);
}
