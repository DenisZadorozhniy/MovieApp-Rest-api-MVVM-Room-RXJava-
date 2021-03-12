package com.example.mycinemaapp.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mycinemaapp.AppExecutors;
import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.models.TVShowDetails;
import com.example.mycinemaapp.models.TvShowReponse;
import com.example.mycinemaapp.repositories.MovieRepository;
import com.example.mycinemaapp.response.MostPopularResponse;
import com.example.mycinemaapp.response.MovieDetailsResponse;
import com.example.mycinemaapp.response.MovieSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    // Livedata
    private MutableLiveData<List<MovieModel>> mMovies;
    private MutableLiveData<TVShowDetails> mDetailsMovies;
    private static MovieApiClient instance;

    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    private RetrieveMostPopularRunnable retrieveMostPopularRunnable;
    private RetrieveMoviesDetails retrieveMoviesDetails;

    public static MovieApiClient getInstance() {
        if(instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mDetailsMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public LiveData<TVShowDetails> getTvShowDetails() {
        return mDetailsMovies;
    }

    // 1 - именно этот метод пустим через классы
    public void SearchMoviesApi (String query, int pageNumber){

        if(retrieveMoviesRunnable !=null){
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MILLISECONDS );
    }

    private class RetrieveMoviesRunnable implements Runnable {
        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            // Получаем Response объекты
            try{
                Response response = getMovies(query, pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getList_movies());
                    if(pageNumber == 1){
                        mMovies.postValue(list);
                    } else {
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error code" +error);
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }

        }
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
            return Servicey.getMovieApi().searchMovie(
                    query,
                    pageNumber
                 );
            }

        private void cancelRequest(){
            Log.v("Tag","Cancelling Search Request");
            cancelRequest = true;
        }

    }

    // метод который пускаем через классы для MostPopular

    public void MostPopularApi (int page){

        if(retrieveMostPopularRunnable !=null){
            retrieveMostPopularRunnable = null;
        }

        retrieveMostPopularRunnable = new RetrieveMostPopularRunnable(page);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMostPopularRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MILLISECONDS );
    }

    private class RetrieveMostPopularRunnable implements Runnable{
        private int page;
        boolean cancelRequest;

        public RetrieveMostPopularRunnable(int page){
            this.page = page;
            cancelRequest = false;
        }


        @Override
        public void run() {
            // Получаем Response объекты
            try {
                Response response = getMostPopular(page).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<MovieModel> list_most_popular = new ArrayList<>(((MostPopularResponse)response.body()).getList_most_popular());
                    if(page == 1){
                        mMovies.postValue(list_most_popular);
                    } else {
                        List<MovieModel> currentMostPopularMovies = mMovies.getValue();
                        currentMostPopularMovies.addAll(list_most_popular);
                        mMovies.postValue(currentMostPopularMovies);
                    }
                } else {
                    String error_mosst_popular = response.errorBody().string();
                    Log.v("Tag", "Error code" +error_mosst_popular);
                    mMovies.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }

        }

        private Call<MostPopularResponse> getMostPopular(int page){
            return Servicey.getMovieApi().mostPopular(
                    page
            );
        }

        private void cancelRequest(){
            Log.v("Tag","Cancelling Search Request");
            cancelRequest = true;
        }
    }

    // метод который пускаем через классы для MovieDetails
    public void movieDetails(String query){

        if(retrieveMoviesDetails != null){
            retrieveMoviesDetails = null;
        }

    retrieveMoviesDetails = new RetrieveMoviesDetails(query);
    final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesDetails);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MILLISECONDS );
    }

     private class RetrieveMoviesDetails implements Runnable{

        private String query;
        boolean cancelRequest;

        public RetrieveMoviesDetails(String query){
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {
            // Получаем Response объекты
            Log.v("Tag", "Loading movie details");
            try {
                Response<MovieDetailsResponse> response = getTvShowDetails(query).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){

                    MovieDetailsResponse tvShowResponse = response.body();
                    Log.v("Tag", "tvShowDetails body" +tvShowResponse);
                    Log.v("Tag", "_______________"  );

                    mDetailsMovies.postValue(tvShowResponse.getTv_details());
                    Log.v("Tag", "tvShowResponse.getTv_details() is" +mDetailsMovies);

                } else {
                    String error_details = response.errorBody().string();
                    Log.v("Tag", "Error code" +error_details);
                    mDetailsMovies.postValue(null);

                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("Tag", "Error code" );
            }

        }
        private Call<MovieDetailsResponse> getTvShowDetails(String query){
            return Servicey.getMovieApi().movieDetails(query);
        }

        private void cancelRequest(){
            Log.v("Tag","Cancelling Search Request");
            cancelRequest = true;
        }

    }


}
