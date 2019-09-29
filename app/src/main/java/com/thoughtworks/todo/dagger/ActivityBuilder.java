package com.thoughtworks.todo.dagger;


import com.thoughtworks.todo.dagger.annotations.scopes.ActivityScope;
import com.thoughtworks.todo.dagger.modules.TodoModule;
import com.thoughtworks.todo.todo.view.TodoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = {TodoModule.class, FragmentProvider.class})
    abstract TodoActivity bindTodoActivity();

}
