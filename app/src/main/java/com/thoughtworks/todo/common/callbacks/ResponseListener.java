package com.thoughtworks.todo.common.callbacks;


import com.google.gson.Gson;
import com.thoughtworks.todo.common.model.Errors;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class ResponseListener<T> implements Callback<T> {

    protected abstract void onResponse(T t, Headers headers);

    protected abstract void onError(String message);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponse(response.body(), response.headers());
        } else {
            if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                try {
                    Errors errors = new Gson().fromJson(response.errorBody().string(), Errors.class);
                    onError(errors.getError().getMessage());
                    return;
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }
            onFailure(call, new Throwable());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof IOException) {
            onError("Please check your network connection and try again");
        } else {
            onError("Something went wrong, try again later");
        }
    }
}
