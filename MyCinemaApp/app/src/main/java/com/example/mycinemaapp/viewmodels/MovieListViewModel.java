package com.example.mycinemaapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycinemaapp.database.TVShowDatabase;
import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.models.TVShowDetails;
import com.example.mycinemaapp.repositories.MostPopularRepository;
import com.example.mycinemaapp.repositories.MovieDetailsRepository;
import com.example.mycinemaapp.repositories.MovieRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class MovieListViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private MostPopularRepository mostPopularRepository;
    private MovieDetailsRepository movieDetailsRepository;

    private TVShowDatabase tvShowDatabase;

    public MovieListViewModel(@NonNull Application application){
        super(application);
        movieRepository = MovieRepository.getInstance();
        mostPopularRepository = MostPopularRepository.getInstance();
        movieDetailsRepository = MovieDetailsRepository.getInstance();

        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    public LiveData<List<MovieModel>> getmMovies() {
        return movieRepository.getMovie();
    }

    public LiveData<List<MovieModel>> getMostPopularMovies(int page) {
        return mostPopularRepository.getMostPopular();
    }

    public LiveData<TVShowDetails> getDetails(){
        return movieDetailsRepository.getTvShowDetails();
    }

    // 3 - Calling method in viewmodel
    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchNextPage(){
        movieRepository.searchNextPage();
    }

       // 3 - Calling method in viewmodel (MostPopular)
    public void mostPopularApi(int page){
        mostPopularRepository.mostPopularApi(page);
    }

    public void searchNextPageMostPopular(){
        mostPopularRepository.searchNextPage();
    }

    // 3 - Calling method in viewmodel (Details)
    public void movieDetailsApi (String query){
        movieDetailsRepository.movieDetailsApi(query);
    }

    // Work with database
    public Completable addToWatchList(MovieModel movieModel){
        return tvShowDatabase.tvShowDao().addToWatchlist(movieModel);
    }

    public Completable removeFromWatchList(MovieModel movieModel){
        return tvShowDatabase.tvShowDao().removeFromWatchlist(movieModel);
    }

    public Flowable<List<MovieModel>> loadWatchList(){
        return tvShowDatabase.tvShowDao().getWatchlist();
    }


}
