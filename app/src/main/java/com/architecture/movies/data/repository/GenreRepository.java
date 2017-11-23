package com.architecture.movies.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.architecture.movies.AppExecutor;
import com.architecture.movies.data.NetworkBoundResource;
import com.architecture.movies.data.Resource;
import com.architecture.movies.data.local.dao.GenreDao;
import com.architecture.movies.data.local.dao.MovieWithGenreDao;
import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.data.remote.ApiResponse;
import com.architecture.movies.data.remote.GenreResponse;
import com.architecture.movies.data.remote.WebService;
import com.architecture.movies.util.Constants;
import com.architecture.movies.util.RateLimiter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GenreRepository {

    private final WebService webService;
    private final AppExecutor executor;
    private final GenreDao genreDao;
    private final MovieWithGenreDao movieWithGenreDao;
    private RateLimiter<String> rateLimiter = new RateLimiter<>(5, TimeUnit.MINUTES);

    @Inject
    public GenreRepository(WebService webService, AppExecutor executor, GenreDao genreDao, MovieWithGenreDao movieWithGenreDao) {
        this.webService = webService;
        this.executor = executor;
        this.genreDao = genreDao;
        this.movieWithGenreDao = movieWithGenreDao;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        return new NetworkBoundResource<List<GenreEntity>, GenreResponse<List<GenreEntity>>>(executor) {

            @Override
            protected void saveCallResult(@NonNull GenreResponse item) {
                genreDao.saveGenres(item.getGenres());
            }

            @NonNull
            @Override
            protected LiveData<List<GenreEntity>> loadFromDb() {
                return genreDao.loadGenres();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenreResponse<List<GenreEntity>>>> createCall() {
                LiveData<ApiResponse<GenreResponse<List<GenreEntity>>>> genres = webService.getGenres();
                return genres;
            }


            @Override
            protected boolean shouldFetch(@Nullable List<GenreEntity> data) {
                //return true;
                return data == null || data.isEmpty() || rateLimiter.shouldFetch(Constants.MOVIE_GENRES);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<MovieEntity>>> getGenreMovies(int genreId) {
        return new NetworkBoundResource<List<MovieEntity>, GenreEntity>(executor) {

            @Override
            protected void saveCallResult(@NonNull GenreEntity item) {
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenreEntity>> createCall() {
                return null;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MovieEntity> data) {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch(Constants.MOVIE_GENRES);
            }
        }.asLiveData();
    }

}
