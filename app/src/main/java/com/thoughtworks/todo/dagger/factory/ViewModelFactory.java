package com.thoughtworks.todo.dagger.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.thoughtworks.todo.data.Repository;
import com.thoughtworks.todo.todo.viewmodel.TodoViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;
    private final Application application;

    @Inject
    public ViewModelFactory(@NonNull Application application, Repository repository) {
        this.repository = repository;
        this.application = application;
    }

    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TodoViewModel.class)) {
            return (T) new TodoViewModel(application, repository);
        }
        throw new IllegalArgumentException("Unknown account viewModel class");
    }
}