package com.architecture.movies.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Transaction;

import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.data.local.entity.MovieWithGenre;

import java.util.List;

@Dao
public interface MovieWithGenreDao {
//USED FOR MANY TO MANY RELATIONS

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM MovieEntity " +
            "INNER JOIN MovieWithGenre ON MovieEntity.id = MovieWithGenre.movieId " +
            " WHERE MovieWithGenre.genreId = :genreId")
    LiveData<List<MovieEntity>> getGenreMovies(int genreId);


    //List all genreIds by movies
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM GenreEntity " +
            "INNER JOIN MovieWithGenre ON GenreEntity.id = MovieWithGenre.genreId " +
            "WHERE MovieWithGenre.movieId= :movieId")
    LiveData<List<GenreEntity>> getMoviesGenre(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMoviesWithGenres(List<MovieWithGenre> movieWithGenres);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovieWithGenre(MovieWithGenre movieWithGenre);

    @Transaction
    @Query("SELECT * FROM MovieWithGenre WHERE movieId = :movieId")
    LiveData<MovieWithGenre> loadMovie(int movieId);
}
