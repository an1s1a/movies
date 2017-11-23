package com.architecture.movies.util;

import android.os.SystemClock;
import android.util.ArrayMap;

import java.util.concurrent.TimeUnit;

public class RateLimiter<KEY> {

    private ArrayMap<KEY, Long> timestamps = new ArrayMap<>();
    private final Long timeout;

    public RateLimiter(int timeout, TimeUnit timeUnit) {
        this.timeout = timeUnit.toMillis(timeout);
    }

    public synchronized boolean shouldFetch(KEY key) {
        Long lastFetched = timestamps.get(key);
        long now = SystemClock.uptimeMillis();
        if (lastFetched == null) {
            timestamps.put(key, now);
            return true;
        }
        return false;
    }
}
