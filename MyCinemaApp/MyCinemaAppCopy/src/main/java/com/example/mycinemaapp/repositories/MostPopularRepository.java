package com.example.mycinemaapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.request.MovieApiClient;

import java.util.List;

public class MostPopularRepository {
    private static MostPopularRepository instance;
    private MovieApiClient movieApiClient;

    private int mPage;

    public static MostPopularRepository getInstance() {
        if(instance == null){
            instance = new MostPopularRepository();
        }
        return instance;
    }

    private MostPopularRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMostPopular(){
        return movieApiClient.getMovies();
    }

    // 2 Calling the method in repository
    public void mostPopularApi(int page){

        this.mPage = page;

        movieApiClient.MostPopularApi(page);
    }

    public void searchNextPage() {
        mostPopularApi(mPage +1);
    }


}
