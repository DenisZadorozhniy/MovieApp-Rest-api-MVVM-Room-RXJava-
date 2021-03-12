package com.example.mycinemaapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mycinemaapp.models.MovieModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TVShowDao {

    @Query(" SELECT * FROM tvShows ")
    Flowable<List<MovieModel>> getWatchlist();

    // Меняем старую запись на новую
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchlist(MovieModel movieModel);

    @Delete
    Completable removeFromWatchlist(MovieModel movieModel);



}
