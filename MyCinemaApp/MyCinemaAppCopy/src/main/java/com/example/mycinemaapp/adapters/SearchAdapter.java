package com.example.mycinemaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.MovieModel;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> list_movie_model;
    private OnMovieListener onMovieListener;

    public SearchAdapter(OnMovieListener onMovieListener) {
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
                .load(list_movie_model.get(position).getThumbnailPath())
                .into(((MovieViewHolder)holder).roundedImageView);
        ((MovieViewHolder)holder).text_title.setText(list_movie_model.get(position).getName());
        ((MovieViewHolder)holder).text_network.setText(list_movie_model.get(position).getNetwork());
        ((MovieViewHolder)holder).text_started.setText(list_movie_model.get(position).getStartDate());
        ((MovieViewHolder)holder).text_status.setText(list_movie_model.get(position).getStatus());



    }

    @Override
    public int getItemCount() {
        if (list_movie_model != null){
            return list_movie_model.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> list_movie_model) {
        this.list_movie_model = list_movie_model;
        notifyDataSetChanged();
    }

    // Получаем id записи при нажатии на нее
    public MovieModel getSelectedMovie(int position){
        if(list_movie_model != null && list_movie_model.size() > 0){
            return list_movie_model.get(position);
        }
        return null;
    }
}
