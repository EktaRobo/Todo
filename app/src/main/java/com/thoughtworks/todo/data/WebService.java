package com.thoughtworks.todo.data;


import com.thoughtworks.todo.todo.model.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {

    @GET("items")
    Call<List<Todo>> fetchTodoList();
}
