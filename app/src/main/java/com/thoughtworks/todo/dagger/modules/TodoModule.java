package com.thoughtworks.todo.dagger.modules;

import androidx.lifecycle.ViewModelProviders;

import com.thoughtworks.todo.dagger.annotations.qualifiers.TodoActivityQualifier;
import com.thoughtworks.todo.dagger.annotations.qualifiers.TodoFragmentQualifier;
import com.thoughtworks.todo.dagger.annotations.scopes.ActivityScope;
import com.thoughtworks.todo.dagger.annotations.scopes.FragmentScope;
import com.thoughtworks.todo.dagger.factory.ViewModelFactory;
import com.thoughtworks.todo.todo.view.TodoActivity;
import com.thoughtworks.todo.todo.view.TodoFragment;
import com.thoughtworks.todo.todo.viewmodel.TodoViewModel;

import dagger.Module;
import dagger.Provides;

@Module()
public class TodoModule {

    @Provides
    @FragmentScope
    @TodoFragmentQualifier
    TodoViewModel provideTodoViewModelForFragment(TodoFragment fragment, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(fragment, viewModelFactory).get(TodoViewModel.class);
    }

    @Provides
    @ActivityScope
    @TodoActivityQualifier
    TodoViewModel provideTodoViewModelForActivity(TodoActivity todoActivity, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(todoActivity, viewModelFactory).get(TodoViewModel.class);
    }

}
