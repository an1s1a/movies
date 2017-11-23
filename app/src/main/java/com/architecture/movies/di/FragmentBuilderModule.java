package com.architecture.movies.di;

import com.architecture.movies.ui.movie.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MovieListFragment getMovieListFragment();
}
