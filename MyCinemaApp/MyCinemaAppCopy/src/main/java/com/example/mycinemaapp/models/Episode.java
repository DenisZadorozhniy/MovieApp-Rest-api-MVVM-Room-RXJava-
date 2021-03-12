package com.example.mycinemaapp.models;

import com.google.gson.annotations.SerializedName;

public class Episode {
    @SerializedName("season")
    private String season;

    @SerializedName("name")
    private String name;

    @SerializedName("episode")
    private String episode;

    @SerializedName("air_date")
    private String air_date;

    public String getSeason() {
        return season;
    }

    public String getName() {
        return name;
    }

    public String getEpisode() {
        return episode;
    }

    public String getAir_date() {
        return air_date;
    }
}
