package com.architecture.movies.ui.movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.architecture.movies.R;
import com.architecture.movies.data.Resource;
import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.databinding.MovieBinding;
import com.architecture.movies.ui.genre.GenreViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String KEY_MOVIE_ID = "KEY_MOVIE_ID";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MovieViewModel movieViewModel;
    private GenreViewModel genreViewModel;
    MovieBinding movieBinding;

    public static Intent newIntent(Context context, int movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(KEY_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        genreViewModel = ViewModelProviders.of(this, viewModelFactory).get(GenreViewModel.class);
        movieBinding = DataBindingUtil.setContentView(this, R.layout.movie);


        int movieId = getIntent().getIntExtra(KEY_MOVIE_ID, 0);
        LiveData<Resource<MovieEntity>> movie = movieViewModel.getMovie(movieId);
        movie.observe(this, movieEntityResource -> {
            movieBinding.setMovie(movieEntityResource.data);
        });

        LiveData<Resource<List<GenreEntity>>> gen = genreViewModel.getGenres();

        LiveData<Resource<List<GenreEntity>>> res = movieViewModel.getMovieGenres(movieId);
        movieViewModel.getMovies();
    }
}
