package com.example.mycinemaapp.response;

import com.example.mycinemaapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("total")
    @Expose
    private int total_count;

    // Все объекты которые выдаються в поиске помещаем  список.
    @SerializedName("tv_shows")
    @Expose
    private List<MovieModel> list_movies;

    public int getTotal_count() {
        return total_count;
    }

    public List<MovieModel> getList_movies() {
        return list_movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", list_movies=" + list_movies +
                '}';
    }
}
