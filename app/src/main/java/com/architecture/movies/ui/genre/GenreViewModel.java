package com.architecture.movies.ui.genre;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.architecture.movies.data.Resource;
import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.data.repository.GenreRepository;

import java.util.List;

import javax.inject.Inject;

public class GenreViewModel extends ViewModel {

    private GenreRepository genreRepository;

    @Inject
    public GenreViewModel(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres(){
        return genreRepository.getGenres();
    }

    public LiveData<Resource<List<MovieEntity>>> getGenreMovies(int genreId){
        return genreRepository.getGenreMovies(genreId);
    }
}
