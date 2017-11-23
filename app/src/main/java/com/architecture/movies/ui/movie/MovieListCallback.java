package com.architecture.movies.ui.movie;

import android.view.View;

import com.architecture.movies.data.local.entity.MovieEntity;

public interface MovieListCallback {
    void onMovieClicked(MovieEntity movieEntity, View sharedView);
}
