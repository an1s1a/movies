package com.architecture.movies.data.repository;

import com.architecture.movies.AppExecutor;
import com.architecture.movies.data.local.dao.PageDao;
import com.architecture.movies.data.remote.WebService;
import com.architecture.movies.util.RateLimiter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PageRepository {

    private final WebService webService;
    private final AppExecutor executor;
    private final PageDao pageDao;
    private RateLimiter rateLimiter = new RateLimiter<>(20, TimeUnit.MINUTES);

    @Inject
    public PageRepository(WebService webService, AppExecutor executor, PageDao pageDao) {
        this.webService = webService;
        this.executor = executor;
        this.pageDao = pageDao;
    }
}
