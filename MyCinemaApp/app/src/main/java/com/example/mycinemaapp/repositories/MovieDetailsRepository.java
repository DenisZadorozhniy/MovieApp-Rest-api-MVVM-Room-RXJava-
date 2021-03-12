package com.example.mycinemaapp.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.models.TVShowDetails;
import com.example.mycinemaapp.request.MovieApiClient;
import com.example.mycinemaapp.response.MovieDetailsResponse;
import com.example.mycinemaapp.utils.MovieApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository {

    private static MovieDetailsRepository instance;
    private MovieApiClient movieApiClient;

    public static MovieDetailsRepository getInstance() {
        if(instance == null){
            instance = new MovieDetailsRepository();
        }
        return instance;
    }

    private MovieDetailsRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData <TVShowDetails> getTvShowDetails(){
        return movieApiClient.getTvShowDetails();

    }

    // 2 - Calling the method in repository
    public void movieDetailsApi(String query){
        movieApiClient.movieDetails(query);
    }

}
