package com.thoughtworks.todo.data.db;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.thoughtworks.todo.todo.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    //TODO:Delete
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTodo(List<Todo> todoList);

    @Query("SELECT * from todo")
    LiveData<List<Todo>> getTodoList();

    @Query("SELECT * from todo where isTodayList = :isTodayList")
    LiveData<List<Todo>> getTodayTodoList(boolean isTodayList);
}
