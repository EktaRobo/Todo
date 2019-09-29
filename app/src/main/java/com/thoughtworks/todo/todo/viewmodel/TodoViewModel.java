package com.thoughtworks.todo.todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.thoughtworks.todo.common.model.Resource;
import com.thoughtworks.todo.common.viewmodel.BaseViewModel;
import com.thoughtworks.todo.data.Repository;
import com.thoughtworks.todo.todo.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoViewModel extends BaseViewModel {

    private final LiveData<Resource<List<Todo>>> todoListData;
    private Repository repository;
    private MutableLiveData<Boolean> loadList = new MutableLiveData<>();

    public TodoViewModel(@NonNull Application application, Repository repository) {
        super(application);
        todoListData = Transformations.switchMap(loadList, loadList -> repository.getTodoList());
        this.repository = repository;
    }

    public LiveData<Resource<List<Todo>>> getTodoListData() {
        return todoListData;
    }

    public void setLoadList(boolean loadList) {
        this.loadList.setValue(loadList);
    }

    public LiveData<List<Todo>> getItemsFromDb(boolean isTodayList) {
        return repository.getTodoListFromDb(isTodayList);
    }

    public List<Todo> getPendingListItems(List<Todo> todoList) {
        List<Todo> pendingTodoList = new ArrayList<>();
        for (Todo todo: todoList) {
            if (!todo.isCompleted()) {
                pendingTodoList.add(todo);
            }
        }
        return pendingTodoList;
    }

    public List<Todo> getCompletedListItems(List<Todo> todoList) {
        List<Todo> pendingTodoList = new ArrayList<>();
        for (Todo todo: todoList) {
            if (todo.isCompleted()) {
                pendingTodoList.add(todo);
            }
        }
        return pendingTodoList;
    }

}
