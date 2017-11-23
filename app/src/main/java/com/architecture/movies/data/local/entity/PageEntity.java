package com.architecture.movies.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity
public class PageEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int page;
    private int total_results;
    private int total_pages;
    @Ignore
    private List<MovieEntity> results;

    @Ignore
    public PageEntity() {
    }

    public PageEntity(int id, int page, int total_results, int total_pages) {
        this.id = id;
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}

