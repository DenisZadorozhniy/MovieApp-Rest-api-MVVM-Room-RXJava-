package com.example.mycinemaapp.utils;

import com.example.mycinemaapp.models.TVShowDetails;
import com.example.mycinemaapp.response.MostPopularResponse;
import com.example.mycinemaapp.response.MovieDetailsResponse;
import com.example.mycinemaapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    //  /api/search?q=:search&page=:page
    // https://www.episodate.com/api/search?q=arrow&page=1
    @GET("https://www.episodate.com/api/search")
    Call<MovieSearchResponse> searchMovie(
      @Query("q") String query,
      @Query("page") int pageNumber
    );

    // Most Popular TV Shows
    // https://www.episodate.com/api/most-popular?page=1
    @GET("https://www.episodate.com/api/most-popular")
    Call<MostPopularResponse> mostPopular(
      @Query("page") int page
    );

    // Details
    // https://www.episodate.com/api/show-details?q=29560
    @GET("https://www.episodate.com/api/show-details")
    Call<MovieDetailsResponse> movieDetails(
      @Query("q") String query
    );


}
