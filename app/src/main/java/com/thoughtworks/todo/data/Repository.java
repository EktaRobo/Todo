package com.thoughtworks.todo.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thoughtworks.todo.common.callbacks.ResponseListener;
import com.thoughtworks.todo.common.model.Resource;
import com.thoughtworks.todo.data.db.TodoDao;
import com.thoughtworks.todo.todo.model.Todo;
import com.thoughtworks.todo.utils.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import okhttp3.Headers;
import retrofit2.Call;

public class Repository {
    private final WebService webService;
    private final TodoDao todoDao;
    private final AppExecutors executors;

    @Inject
    public Repository(WebService webService, TodoDao todoDao, AppExecutors executors) {
        this.webService = webService;
        this.todoDao = todoDao;
        this.executors = executors;
    }


    public LiveData<Resource<List<Todo>>> getTodoList() {
        MutableLiveData<Resource<List<Todo>>> data = new MutableLiveData<>();
        Call<List<Todo>> call = webService.fetchTodoList();
        data.setValue(Resource.loading());
        call.enqueue(new ResponseListener<List<Todo>>() {

            @Override
            protected void onResponse(List<Todo> response, Headers headers) {
                if (response != null) {
                    data.setValue(Resource.success(response));
                    executors.disk().execute(() -> todoDao.insertTodo(response));
                }
                else {
                    data.setValue(Resource.error("Something went wrong"));
                }
            }

            @Override
            protected void onError(String message) {
                data.setValue(Resource.error(message));
            }
        });
        return data;
    }

    public LiveData<List<Todo>> getTodoListFromDb() {
        return todoDao.getTodoList();
    }

    public LiveData<List<Todo>> getTodoListFromDb(boolean isToday) {
        return todoDao.getTodayTodoList(isToday);
    }



}
