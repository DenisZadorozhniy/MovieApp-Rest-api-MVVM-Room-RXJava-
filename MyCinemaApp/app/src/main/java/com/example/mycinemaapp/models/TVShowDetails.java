package com.example.mycinemaapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class TVShowDetails implements Parcelable {

    @SerializedName("description")
    private String description;

   @SerializedName("runtime")
    private String runtime;

    @SerializedName("image_path")
    private String image_path;

    @SerializedName("rating")
    private String rating;

   @SerializedName("pictures")
    private String[] pictures;



    public TVShowDetails(  String description, String runtime, String image_path,
            String rating,  String[] pictures  ) {

       this.description = description;
       this.runtime = runtime;
       this.image_path = image_path;
       this.rating = rating;
       this.pictures = pictures;

    }

    protected TVShowDetails (Parcel in){
        description = in.readString();
        runtime = in.readString();
        image_path = in.readString();
        rating = in.readString();
        pictures =  in.createStringArray();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      //  dest.writeString(url);
        dest.writeString(description);
        dest.writeString(runtime);
        dest.writeString(image_path);
         dest.writeString(rating);
   /*     dest.writeString(genres); */
           dest.writeString(String.valueOf(pictures));
    }

    public static final Creator<TVShowDetails> CREATOR = new Creator<TVShowDetails>() {
        @Override
        public TVShowDetails createFromParcel(Parcel in) {
            return new TVShowDetails(in);
        }

        @Override
        public TVShowDetails[] newArray(int size) {
            return new TVShowDetails[size];
        }
    };





   public String getDescription() {
        return description;
    }

   public String getRuntime() {
        return runtime;
    }

   public String getImage_path() {
        return image_path;
    }

   public String getRating() {
        return rating;
    }

  /* public String getGenres() {
        return genres;
    } */

   public String[] getPictures() {
        return pictures;
    }

    @Override
    public String toString() {
        return "TVShowDetails{" +
            //    "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", runtime='" + runtime + '\'' +
                ", image_path='" + image_path + '\'' +
               ", rating='" + rating + '\'' +
              /*  ", genres='" + genres + '\'' + */
                 ", pictures=" + Arrays.toString(pictures) +
                '}';
    }
}
