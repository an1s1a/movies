package com.architecture.movies.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public Resource(@NonNull Status status, @Nullable String message, @Nullable T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, null, data);
    }

    public static <T> Resource<T> error(String message, @Nullable T data) {
        return new Resource<>(Status.ERROR, message, data);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, null, data);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message+ " + message +
                ", data=" + data +
                "}";
    }
}
