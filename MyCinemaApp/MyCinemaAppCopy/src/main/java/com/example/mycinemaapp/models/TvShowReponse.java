package com.example.mycinemaapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShowReponse implements Parcelable {
    @SerializedName("tvShow")
    @Expose
    TVShowDetails tvShowDetails;


    protected TvShowReponse(Parcel in) {
        tvShowDetails = in.readParcelable(TVShowDetails.class.getClassLoader());
    }

    public static final Creator<TvShowReponse> CREATOR = new Creator<TvShowReponse>() {
        @Override
        public TvShowReponse createFromParcel(Parcel in) {
            return new TvShowReponse(in);
        }

        @Override
        public TvShowReponse[] newArray(int size) {
            return new TvShowReponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(tvShowDetails,flags);
    }

    public TvShowReponse(TVShowDetails tvShowDetails) {
        this.tvShowDetails = tvShowDetails;
    }

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
