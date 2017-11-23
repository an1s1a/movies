package com.architecture.movies.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.architecture.movies.data.local.MovieDataBase;
import com.architecture.movies.data.local.dao.GenreDao;
import com.architecture.movies.data.local.dao.MovieDao;
import com.architecture.movies.data.local.dao.MovieWithGenreDao;
import com.architecture.movies.data.local.dao.PageDao;
import com.architecture.movies.data.remote.ApiConstants;
import com.architecture.movies.data.remote.RequestInterceptor;
import com.architecture.movies.data.remote.WebService;
import com.architecture.movies.util.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    WebService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build();
        return retrofit.create(WebService.class);
    }

    @Provides
    @Singleton
    MovieDataBase provideMovieDataBase(Application application) {
        return Room.databaseBuilder(application, MovieDataBase.class, "movieDb.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    GenreDao provideGenreDao(MovieDataBase db) {
        return db.genreDao();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(MovieDataBase db) {
        return db.movieDao();
    }

    @Provides
    @Singleton
    PageDao providePageDao(MovieDataBase db) {
        return db.pageDao();
    }

    @Provides
    @Singleton
    MovieWithGenreDao provideMovieWithGenreDao(MovieDataBase db) {
        return db.movieWithGenreDao();
    }
}
