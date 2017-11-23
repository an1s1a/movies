package com.architecture.movies.data.remote;

import com.google.gson.annotations.Expose;
import com.architecture.movies.data.local.entity.GenreEntity;

import java.util.ArrayList;

public class GenreResponse<T> {

    @Expose
    private ArrayList<GenreEntity> genres;

    public ArrayList<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreEntity> genres) {
        this.genres = genres;
    }
}
