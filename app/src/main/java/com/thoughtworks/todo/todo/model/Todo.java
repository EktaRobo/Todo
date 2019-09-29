package com.thoughtworks.todo.todo.model;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.thoughtworks.todo.utils.Constants;
import com.thoughtworks.todo.utils.DateUtils;

@Keep
@Entity
public class Todo {
    @PrimaryKey
    private int id;
    private String description;
    private String scheduledDate;
    private String status;
    private boolean isTodayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public String getDate() {
        return DateUtils.getDate(scheduledDate, DateUtils.DATE_FORMAT, DateUtils.FORMATTED_DATE);
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return Constants.TodoStatus.COMPLETED.equals(status);
    }

    public boolean isTodayList() {
        return DateUtils.getDate(scheduledDate, DateUtils.DATE_FORMAT, DateUtils.YEAR_FIRST).equals(DateUtils.getCurrentDate(DateUtils.YEAR_FIRST));
    }

    public void setTodayList(boolean todayList) {
        isTodayList = todayList;
    }

}
