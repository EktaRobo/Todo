package com.thoughtworks.todo.todo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.todo.R;
import com.thoughtworks.todo.common.view.DaggerBaseFragment;
import com.thoughtworks.todo.dagger.annotations.qualifiers.TodoFragmentQualifier;
import com.thoughtworks.todo.databinding.FragmentTodoBinding;
import com.thoughtworks.todo.todo.model.Todo;
import com.thoughtworks.todo.todo.view.adapters.TodoListAdapter;
import com.thoughtworks.todo.todo.viewmodel.TodoViewModel;
import com.thoughtworks.todo.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class TodoFragment extends DaggerBaseFragment {

    private FragmentTodoBinding binding;
    @Inject
    @TodoFragmentQualifier
    TodoViewModel viewModel;

    public static TodoFragment newInstance(boolean isTodayList) {

        Bundle args = new Bundle();
        args.putBoolean(Constants.TODAY, isTodayList);
        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TodoFragment() {
        //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        hideProgressBar();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false);
        viewModel.getItemsFromDb(getArguments().getBoolean(Constants.TODAY)).observe(this, todoList -> {
            List<Todo> pendingListItems = viewModel.getPendingListItems(todoList);
            List<Todo> completedListItems = viewModel.getCompletedListItems(todoList);
            binding.pendingItemsList.setAdapter(new TodoListAdapter(pendingListItems, (todo, view, position) -> {
                pendingListItems.remove(todo);
                completedListItems.add(todo);
                RecyclerView.Adapter adapter = binding.pendingItemsList.getAdapter();
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, pendingListItems.size());
                binding.completedItemsList.getAdapter().notifyItemInserted(completedListItems.size() - 1);
            }, true));
            binding.completedItemsList.setAdapter(new TodoListAdapter(completedListItems,(todo, view, position) -> {

            }, false));
        });
        return addView(binding.getRoot());
    }
}
