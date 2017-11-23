package com.architecture.movies.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(primaryKeys = {"movieId", "genreId"}, foreignKeys = {@ForeignKey(entity = MovieEntity.class, parentColumns = "id", childColumns = "movieId"),
        @ForeignKey(entity = GenreEntity.class, parentColumns = "id", childColumns = "genreId")})
public class MovieWithGenre {

    private int movieId;

    private int genreId;

    public MovieWithGenre(int movieId, int genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

}

