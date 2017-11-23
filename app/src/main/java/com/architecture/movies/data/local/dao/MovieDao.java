package com.architecture.movies.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.data.local.entity.PageEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE id = :movieId")
    LiveData<MovieEntity> loadMovie(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovie(MovieEntity movieEntity);

    @Query("SELECT* FROM PageEntity")
    LiveData<PageEntity> loadPage();

    @Query("SELECT * FROM MovieEntity")
    LiveData<List<MovieEntity>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePage(PageEntity pageEntity);
}
