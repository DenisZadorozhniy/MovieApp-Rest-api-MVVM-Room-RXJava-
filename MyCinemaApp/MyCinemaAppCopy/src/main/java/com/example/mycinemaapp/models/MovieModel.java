package com.example.mycinemaapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tvShows")
public class MovieModel implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("country")
    private String country;

    @SerializedName("network")
    private String network;

    @SerializedName("status")
    private String status;

    @SerializedName("image_thumbnail_path")
    private String thumbnailPath;

    @SerializedName("description")
    private String description;

    @SerializedName("rating")
    private String rating;

 

    public MovieModel(int id, String name, String startDate, String country,
                      String network, String status, String thumbnailPath, String description, String rating) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.country = country;
        this.network = network;
        this.status = status;
        this.thumbnailPath = thumbnailPath;
        this.description = description;
        this.rating = rating;

    }


    protected MovieModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        startDate = in.readString();
        country = in.readString();
        network = in.readString();
        status = in.readString();
        thumbnailPath = in.readString();
        description = in.readString();
        rating = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(startDate);
        dest.writeString(country);
        dest.writeString(network);
        dest.writeString(status);
        dest.writeString(thumbnailPath);
        dest.writeString(description);
        dest.writeString(rating);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getCountry() {
        return country;
    }

    public String getNetwork() {
        return network;
    }

    public String getStatus() {
        return status;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    // Set


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
