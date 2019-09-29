package com.thoughtworks.todo.todo.view;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.thoughtworks.todo.R;
import com.thoughtworks.todo.common.model.Resource;
import com.thoughtworks.todo.common.view.DaggerBaseActivity;
import com.thoughtworks.todo.dagger.annotations.qualifiers.TodoActivityQualifier;
import com.thoughtworks.todo.databinding.ActivityMainBinding;
import com.thoughtworks.todo.todo.model.Todo;
import com.thoughtworks.todo.todo.view.adapters.TodoListPagerAdapter;
import com.thoughtworks.todo.todo.viewmodel.TodoViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

import javax.inject.Inject;

public class TodoActivity extends DaggerBaseActivity {

    private ActivityMainBinding binding;
    @Inject
    @TodoActivityQualifier
    TodoViewModel viewModel;
    private final Observer<Resource<List<Todo>>> todoObserver = resource -> {
        switch (resource.status) {
            case SUCCESS:
                hideProgressBar();
                binding.viewPager.setAdapter(new TodoListPagerAdapter(getSupportFragmentManager()));
                break;
            case ERROR:
                hideProgressBar();
                showSnackBar(resource.message);
                break;
            case LOADING:
                showProgressBar();
                break;
        }
    };

    protected void showSnackBar(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);

    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        addView(binding.getRoot());
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        viewModel.getTodoListData().observe(this, todoObserver);
        viewModel.setLoadList(true);
        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

}
