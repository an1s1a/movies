package com.architecture.movies.ui.movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.architecture.movies.data.Resource;
import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.data.repository.GenreRepository;
import com.architecture.movies.data.repository.MovieRepository;
import com.architecture.movies.data.repository.PageRepository;

import java.util.List;

import javax.inject.Inject;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private PageRepository pageRepository;
    private GenreRepository genreRepository;


    @Inject
    public MovieViewModel(MovieRepository movieRepository, PageRepository pageRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.pageRepository = pageRepository;
        this.genreRepository = genreRepository;
    }

    public LiveData<Resource<MovieEntity>> getMovie(int id) {
        return movieRepository.getMovie(id);
    }

    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        return movieRepository.getMovies();
    }

    public LiveData<Resource<List<GenreEntity>>> getMovieGenres(int movieId) {
        return movieRepository.getMovieGenres(movieId);
    }
}
