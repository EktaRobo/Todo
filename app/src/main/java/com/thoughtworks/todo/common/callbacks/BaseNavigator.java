package com.thoughtworks.todo.common.callbacks;


import androidx.annotation.StringRes;

public interface BaseNavigator {
    void showProgressBar();
    void hideProgressBar();
    void setToolbarTitle(@StringRes int resId);
}
