package com.example.mycinemaapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;
    private MovieModel movieModel;

    public MovieRecyclerView(List<MovieModel> mMovies,OnMovieListener onMovieListener) {
        this.mMovies = mMovies;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_shows,
                parent, false);

        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Под ImageView использую Glide Library
        Glide.with(holder.itemView.getContext())
                .load(mMovies.get(position).getThumbnailPath())
                .into(((MovieViewHolder)holder).roundedImageView);
        ((MovieViewHolder)holder).text_title.setText(mMovies.get(position).getName());
        ((MovieViewHolder)holder).text_network.setText(mMovies.get(position).getNetwork());
        ((MovieViewHolder)holder).text_started.setText(mMovies.get(position).getStartDate());
        ((MovieViewHolder)holder).text_status.setText(mMovies.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        if (mMovies != null){
            return mMovies.size();
        }
        return 0;
    }



    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();

    }

    // Получаем id записи при нажатии на нее
    public MovieModel getSelectedMovie(int position){
        if(mMovies != null && mMovies.size() > 0){
            return mMovies.get(position);
        }
        return null;
    }

}
