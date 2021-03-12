package com.example.mycinemaapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.MovieModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class WatchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;
    private MovieModel movieModel;

    public WatchListAdapter(List<MovieModel> mMovies, OnMovieListener onMovieListener) {
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
        ((MovieViewHolder)holder).deleteImg.setVisibility(View.VISIBLE);
        ((MovieViewHolder)holder).deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog =  new AlertDialog.Builder(holder.itemView.getContext());
                alertDialog.setMessage("Delete this entry?");
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onMovieListener.removeFromWatchList(mMovies.get(position),position );
                    }
                });
                alertDialog.show();
            }
        });
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

    public void removeMovie(List<MovieModel> mMovies, int position){
        this.mMovies = mMovies;
        mMovies.remove(position);
    }

    // Получаем id записи при нажатии на нее
    public MovieModel getSelectedMovie(int position){
        if(mMovies != null && mMovies.size() > 0){
            return mMovies.get(position);
        }
        return null;
    }

}
