package com.example.mycinemaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.TVShowDetails;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private String[] sliderImages;
    private LayoutInflater layoutInflater;
    private TVShowDetails tvShowDetails;

    public ImageSliderAdapter(Context context, String[] sliderImages) {
        this.sliderImages = sliderImages;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImageSliderAdapter.ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context;
        View view =  layoutInflater.inflate(R.layout.item_container_slider_image, parent, false);
        return new ImageSliderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderAdapter.ImageSliderViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(sliderImages[position])
                .into(holder.imageView_slider);
    }

    @Override
    public int getItemCount() {
        return sliderImages.length;
    }

    public static class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private ImageView imageView_slider;

        public ImageSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.slider_layout);
            imageView_slider = itemView.findViewById(R.id.img_slider);

        }

    }
}
