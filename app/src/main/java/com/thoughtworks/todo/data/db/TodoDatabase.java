package com.thoughtworks.todo.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.thoughtworks.todo.todo.model.Todo;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

}
