package com.architecture.movies.di;


import com.architecture.movies.ui.MainActivity;
import com.architecture.movies.ui.genre.GenreListActivity;
import com.architecture.movies.ui.movie.MovieDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MovieDetailActivity movieDetailActivity();

    @ContributesAndroidInjector
    abstract GenreListActivity genreListActivity();
}
