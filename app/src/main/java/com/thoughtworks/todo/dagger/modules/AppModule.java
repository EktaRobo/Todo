package com.thoughtworks.todo.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.thoughtworks.todo.dagger.annotations.scopes.ApplicationScope;
import com.thoughtworks.todo.utils.AppExecutors;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module
public abstract class AppModule {

    @Binds
    @ApplicationScope
    abstract Context provideContext(Application application);

    @Provides
    @ApplicationScope
    public static AppExecutors provideExecutors() {
        return new AppExecutors();
    }

    /*@Provides
    @ApplicationScope
    public static PreferenceManager providePreferenceManager(Application application) {
        return new PreferenceManager(application);
    }*/

}
