package com.architecture.movies.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.architecture.movies.AppExecutor;
import com.architecture.movies.data.NetworkBoundResource;
import com.architecture.movies.data.Resource;
import com.architecture.movies.data.local.dao.MovieDao;
import com.architecture.movies.data.local.dao.MovieWithGenreDao;
import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.data.local.entity.MovieWithGenre;
import com.architecture.movies.data.remote.ApiResponse;
import com.architecture.movies.data.remote.MovieResponse;
import com.architecture.movies.data.remote.WebService;
import com.architecture.movies.util.Constants;
import com.architecture.movies.util.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieRepository {

    private final WebService webService;
    private final AppExecutor appExecutor;
    private final MovieDao movieDao;
    private final MovieWithGenreDao movieWithGenreDao;
    private RateLimiter<String> rateLimiter = new RateLimiter<>(5, TimeUnit.MINUTES);

    @Inject
    public MovieRepository(WebService webService, AppExecutor appExecutor, MovieDao movieDao, MovieWithGenreDao movieWithGenreDao) {
        this.webService = webService;
        this.appExecutor = appExecutor;
        this.movieDao = movieDao;
        this.movieWithGenreDao = movieWithGenreDao;
    }

    public LiveData<Resource<MovieEntity>> getMovie(int movieId) {
        return new NetworkBoundResource<MovieEntity, MovieEntity>(appExecutor) {


            @Override
            protected void saveCallResult(@NonNull MovieEntity item) {
                movieDao.saveMovie(item);
            }

            @NonNull
            @Override
            protected LiveData<MovieEntity> loadFromDb() {
                return movieDao.loadMovie(movieId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieEntity>> createCall() {
                return webService.getMovie(movieId);
            }

            @Override
            protected boolean shouldFetch(@Nullable MovieEntity data) {
                return data == null;
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        return new NetworkBoundResource<List<MovieEntity>, MovieResponse<List<MovieEntity>>>(appExecutor) {

            @Override
            protected void saveCallResult(@NonNull MovieResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse<List<MovieEntity>>>> createCall() {
                LiveData<ApiResponse<MovieResponse<List<MovieEntity>>>> movie = webService.getHighestRatedMovies();
                return movie;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MovieEntity> data) {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch(Constants.MOVIE_LISTS);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<GenreEntity>>> getMovieGenres(int movieId) {
        return new NetworkBoundResource<List<GenreEntity>, MovieEntity>(appExecutor) {

            @Override
            protected void saveCallResult(@NonNull MovieEntity item) {
                List<MovieWithGenre> movieWithGenreList = new ArrayList<>();
                for (int i = 0; i < item.getGenres().size(); i++) {
                    MovieWithGenre movieWithGenre = new MovieWithGenre(item.getId(), item.getGenres().get(i));
                    movieWithGenreList.add(movieWithGenre);
                }
                movieWithGenreDao.saveMoviesWithGenres(movieWithGenreList);
            }

            @NonNull
            @Override
            protected LiveData<List<GenreEntity>> loadFromDb() {
                LiveData<List<GenreEntity>> genres = movieWithGenreDao.getMoviesGenre(movieId);
                return movieWithGenreDao.getMoviesGenre(movieId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieEntity>> createCall() {
                return webService.getMovie(movieId);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<GenreEntity> data) {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch(Constants.MOVIE_GENRES);
            }
        }.asLiveData();
    }
}
