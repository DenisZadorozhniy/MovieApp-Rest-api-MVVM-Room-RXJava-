package com.example.mycinemaapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycinemaapp.dao.TVShowDao;
import com.example.mycinemaapp.models.MovieModel;

@Database(entities = MovieModel.class, version = 1, exportSchema = false)
public abstract class TVShowDatabase extends RoomDatabase {

    private static TVShowDatabase tvShowDatabase;

    public static synchronized TVShowDatabase getTvShowDatabase(Context context) {
        if (tvShowDatabase == null){
            tvShowDatabase = Room.databaseBuilder(
              context,
              TVShowDatabase.class,
              "tv_show_db"
            ).build();
        }
        return tvShowDatabase;
    }
    public abstract TVShowDao tvShowDao();

}
