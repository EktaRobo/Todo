package com.thoughtworks.todo.common.callbacks;

import android.view.View;


public interface OnItemClickListener<T> {

    void onItemClick(T t, View view, int position);
}
