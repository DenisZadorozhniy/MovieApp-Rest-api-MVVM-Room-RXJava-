package com.example.mycinemaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mycinemaapp.adapters.ImageSliderAdapter;
import com.example.mycinemaapp.models.MovieModel;
import com.example.mycinemaapp.viewmodels.MovieListViewModel;
import com.makeramen.roundedimageview.RoundedImageView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetails extends AppCompatActivity {
    private ImageView back_img;
    private ImageView image_watch_list;
    private RoundedImageView poster_movie;
    private ProgressBar progressBar;
    private ViewPager2 slider;
    private View view;
    private TextView title_movie, text_network, text_started, text_status, description_movie, text_country, rating;

    private MovieListViewModel movieListViewModel;
    private String tvShowId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initialization();
        getTVShowDetails();
    }

    private void initialization() {
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        progressBar = findViewById(R.id.pb_details);
        back_img = findViewById(R.id.back_img);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        slider = findViewById(R.id.sliderViewPager2);
        view = findViewById(R.id.view_fading_edge);
        poster_movie = findViewById(R.id.poster_movie);
        title_movie = findViewById(R.id.title_movie);
        text_network = findViewById(R.id.text_network);
        text_started = findViewById(R.id.text_started);
        text_status = findViewById(R.id.text_status);
        text_country = findViewById(R.id.text_country);
        description_movie = findViewById(R.id.description_movie);
        rating = findViewById(R.id.rating_movie);
        image_watch_list = findViewById(R.id.image_watch_list);

    }

    private void getTVShowDetails(){
        if(getIntent().hasExtra("movie")){

            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            tvShowId = String.valueOf(movieModel.getId());

            detailsApi(tvShowId);

            Glide.with(this)
                    .load(movieModel.getThumbnailPath())
                    .into(poster_movie);
            title_movie.setText(movieModel.getName());
            title_movie.setText(movieModel.getName());
            text_network.setText(movieModel.getNetwork());
            text_started.setText(movieModel.getStartDate());
            text_status.setText(movieModel.getStatus());
            text_country.setText(movieModel.getCountry());
            movieListViewModel.getDetails().observe(
                    this, tvShowDetails-> {
                        if (tvShowDetails !=null){
                            loadImageSlider(tvShowDetails.getPictures());
                            description_movie.setText(tvShowDetails.getDescription());
                            rating.setText(tvShowDetails.getRating());
                            progressBar.setVisibility(View.GONE);

                        }
                    }
            );
            image_watch_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CompositeDisposable().add(movieListViewModel.addToWatchList(movieModel)
                    .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( () -> {
                                image_watch_list.setImageResource(R.drawable.ic_added);
                                Toast.makeText(getApplicationContext(), "Added to watchlist",Toast.LENGTH_SHORT).show();
                            } )
                    );
                }
            });
        }
    }

    private void detailsApi(String query){
        movieListViewModel.movieDetailsApi(query);
    }
    private void loadImageSlider(String[] sliderImages){
        slider.setOffscreenPageLimit(1); //проверить
        slider.setAdapter(new ImageSliderAdapter(this,sliderImages));
        view.setVisibility(View.VISIBLE);
    }
}