package com.architecture.movies.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.architecture.movies.data.local.dao.GenreDao;
import com.architecture.movies.data.local.dao.MovieDao;
import com.architecture.movies.data.local.dao.MovieWithGenreDao;
import com.architecture.movies.data.local.dao.PageDao;
import com.architecture.movies.data.local.entity.GenreEntity;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.data.local.entity.MovieWithGenre;
import com.architecture.movies.data.local.entity.PageEntity;

@Database(entities = {GenreEntity.class, MovieEntity.class, PageEntity.class, MovieWithGenre.class}, version = 15)
public abstract class MovieDataBase extends RoomDatabase {

    public abstract GenreDao genreDao();

    public abstract MovieDao movieDao();

    public abstract PageDao pageDao();

    public abstract MovieWithGenreDao movieWithGenreDao();
}
