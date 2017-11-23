package com.architecture.movies.ui.genre;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.architecture.movies.databinding.ActivityGenreBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class GenreListActivity extends AppCompatActivity implements GenreListCallback {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private GenreViewModel genreViewModel;
    ActivityGenreBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        genreViewModel = ViewModelProviders.of(this, viewModelFactory).get(GenreViewModel.class);
    }
}
