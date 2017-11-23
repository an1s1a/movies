package com.architecture.movies.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.architecture.movies.data.local.entity.PageEntity;

@Dao
public interface PageDao {

    @Query("SELECT * FROM PageEntity")
    LiveData<PageEntity> loadPage();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePage(PageEntity pageEntity);
}
