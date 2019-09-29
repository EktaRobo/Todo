package com.thoughtworks.todo.dagger;



import com.thoughtworks.todo.dagger.annotations.scopes.FragmentScope;
import com.thoughtworks.todo.dagger.modules.TodoModule;
import com.thoughtworks.todo.todo.view.TodoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = {TodoModule.class})
    abstract TodoFragment provideTodoFragment();



}
