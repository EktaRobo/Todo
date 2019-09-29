package com.thoughtworks.todo.dagger;

import android.app.Application;


import com.thoughtworks.todo.TodoApplication;
import com.thoughtworks.todo.dagger.annotations.scopes.ApplicationScope;
import com.thoughtworks.todo.dagger.modules.AppModule;
import com.thoughtworks.todo.dagger.modules.DbModule;
import com.thoughtworks.todo.dagger.modules.RetrofitModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;


@ApplicationScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        RetrofitModule.class,
        DbModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(TodoApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
