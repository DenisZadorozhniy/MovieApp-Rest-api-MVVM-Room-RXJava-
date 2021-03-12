package com.example.mycinemaapp.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mycinemaapp.models.TVShowDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailsResponse {

    @SerializedName("tvShow")
    @Expose
    private TVShowDetails tv_details;

    public TVShowDetails getTv_details() {
        return tv_details;
    }

    @Override
    public String toString() {
        return "MovieDetailsResponse{" +
                "tv_details=" + tv_details +
                '}';
    }


}
