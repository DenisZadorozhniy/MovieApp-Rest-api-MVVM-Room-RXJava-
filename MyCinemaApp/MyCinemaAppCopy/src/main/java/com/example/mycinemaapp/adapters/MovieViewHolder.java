package com.example.mycinemaapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.MovieModel;
import com.makeramen.roundedimageview.RoundedImageView;



public class MovieViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

     RoundedImageView roundedImageView;
     ImageView deleteImg;
     TextView text_title, text_network, text_started, text_status;
     OnMovieListener onMovieListener;


    public MovieViewHolder(@NonNull View itemView,  OnMovieListener onMovieListener ) {
        super(itemView);
        this.onMovieListener = onMovieListener;

        roundedImageView = itemView.findViewById(R.id.image_tv_shows);
        text_title = itemView.findViewById(R.id.text_title);
        text_network = itemView.findViewById(R.id.text_network);
        text_started = itemView.findViewById(R.id.text_started);
        text_status = itemView.findViewById(R.id.text_status);
        deleteImg =  itemView.findViewById(R.id.image_delete);



        itemView.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
