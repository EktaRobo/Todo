package com.thoughtworks.todo.common.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;

    public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String message) {
        return new Resource<>(Status.ERROR, null, message);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

    public T getData() {
        return data;
    }

    public enum Status {
        SUCCESS, ERROR, LOADING
    }

}
