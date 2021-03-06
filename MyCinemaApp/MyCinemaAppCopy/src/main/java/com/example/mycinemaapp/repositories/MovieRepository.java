package com.example.mycinemaapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance() {
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData <List<MovieModel>> getMovie(){
        return movieApiClient.getMovies();
    }

    // 2 - Calling the method in repository
    public void searchMovieApi(String query, int pageNumber){

        mQuery = query;
        mPageNumber = pageNumber;

        movieApiClient.SearchMoviesApi(query, pageNumber);

    }

    public void searchNextPage() {
        searchMovieApi(mQuery, mPageNumber +1);
    }

}