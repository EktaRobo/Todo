package com.thoughtworks.todo.dagger.modules;

import android.content.Context;

import androidx.room.Room;


import com.thoughtworks.todo.dagger.annotations.scopes.ApplicationScope;
import com.thoughtworks.todo.data.db.TodoDao;
import com.thoughtworks.todo.data.db.TodoDatabase;
import com.thoughtworks.todo.utils.Constants;

import dagger.Module;
import dagger.Provides;


@Module
public class DbModule {

    @Provides
    @ApplicationScope
    TodoDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                TodoDatabase.class,
                Constants.DB_NAME
        ).build();
    }

    @Provides
    TodoDao provideDao(TodoDatabase todoDatabase) {
        return todoDatabase.todoDao();
    }
}
