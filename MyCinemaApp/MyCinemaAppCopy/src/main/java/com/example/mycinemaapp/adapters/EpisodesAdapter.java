package com.example.mycinemaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.Episode;
import com.example.mycinemaapp.models.MovieModel;

import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {
    private List<Episode> episodesList;
    private LayoutInflater layoutInflater;

    public EpisodesAdapter(List<Episode> episodesList) {
        this.episodesList = episodesList;

    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_episodes_container, parent, false);
        return new EpisodesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {

       // ((EpisodesViewHolder)holder).navigation_episode.setText("Hello");
        ((EpisodesViewHolder)holder).navigation_episode.setText(episodesList.get(position).getEpisode());
       // ((EpisodesViewHolder)holder).title_series.setText(episodesList.get(position).getName());
       // ((EpisodesViewHolder)holder).date_series.setText(episodesList.get(position).getSeason());

    }

    @Override
    public int getItemCount() {
       return episodesList.size();
    }

    public void setList(List<Episode> episodesList) {
        this.episodesList = episodesList;
        notifyDataSetChanged();
    }

    static class EpisodesViewHolder extends RecyclerView.ViewHolder {

        private TextView navigation_episode, title_series, date_series;

        public EpisodesViewHolder(@NonNull View itemView) {
            super(itemView);
            navigation_episode = itemView.findViewById(R.id.navigation_episode);
            title_series = itemView.findViewById(R.id.title_series);
            date_series = itemView.findViewById(R.id.date_series);
        }
    }
}
