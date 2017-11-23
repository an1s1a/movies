package com.architecture.movies.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.architecture.movies.data.local.entity.GenreEntity;

import java.util.List;

@Dao
public interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(GenreEntity genreEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveGenres(List<GenreEntity> genreEntities);

    @Query("SELECT * FROM GenreEntity")
    LiveData<List<GenreEntity>> loadGenres();

    @Query("SELECT * FROM GenreEntity WHERE id = :genreId")
    LiveData<GenreEntity> loadGenre(int genreId);

}
