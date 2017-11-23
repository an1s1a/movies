package com.architecture.movies.data.remote;

import com.architecture.movies.data.local.entity.MovieEntity;

import java.util.List;

public class MovieResponse<T> {

    private int page;

    private int total_results;

    private int total_pages;

    private List<MovieEntity> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieEntity> getResults() {
        return results;
    }

    public List<Integer> getGenres(int position) {
        return results.get(position).getGenres();
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotale_pages() {
        return total_pages;
    }

    public void setTotale_pages(int totale_pages) {
        this.total_pages = totale_pages;
    }
}
