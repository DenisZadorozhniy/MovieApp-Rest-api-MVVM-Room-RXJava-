package com.example.mycinemaapp.response;

import com.example.mycinemaapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostPopularResponse {
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("pages")
    @Expose
    private int total_pages;

    @SerializedName("tv_shows")
    @Expose
    private List<MovieModel> list_most_popular;

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<MovieModel> getList_most_popular() {
        return list_most_popular;
    }

    @Override
    public String toString() {
        return "MostPopularResponse{" +
                "page=" + page +
                ", total_pages=" + total_pages +
                ", list_most_popular=" + list_most_popular +
                '}';
    }
}
