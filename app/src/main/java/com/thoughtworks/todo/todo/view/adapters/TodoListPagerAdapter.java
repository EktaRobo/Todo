package com.thoughtworks.todo.todo.view.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.thoughtworks.todo.todo.view.TodoFragment;
import com.thoughtworks.todo.utils.Constants;

public class TodoListPagerAdapter extends FragmentPagerAdapter {

    public TodoListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TodoFragment.newInstance(position == 0);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return Constants.TODAY;
        } else {
            return Constants.LATER;
        }
    }
}