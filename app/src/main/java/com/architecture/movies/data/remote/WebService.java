package com.architecture.movies.data.remote;

import android.arch.lifecycle.LiveData;

import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("genre/movie/list")
    LiveData<ApiResponse<GenreResponse<List<GenreEntity>>>> getGenres();

    @GET("discover/movie?sort_by=popularity.desc")
    LiveData<ApiResponse<MovieResponse<List<MovieEntity>>>> getPopularMovies();

    @GET("movie/{movie}")
    LiveData<ApiResponse<MovieEntity>> getMovie(@Path("movie") int movieId);

    @GET("movie/top_rated")
    LiveData<ApiResponse<MovieResponse<List<MovieEntity>>>> getHighestRatedMovies();
}
