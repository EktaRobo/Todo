package com.thoughtworks.todo.common.callbacks;


import androidx.fragment.app.Fragment;

public interface ActivityListener {
    void addFragment(Fragment fragment);
    void replaceFragment(Fragment fragment);
    void addFragment(Fragment fragment, String tag);
    void replaceFragment(Fragment fragment, String tag);
}
