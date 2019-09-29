package com.thoughtworks.todo.todo.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.todo.BR;
import com.thoughtworks.todo.R;
import com.thoughtworks.todo.common.callbacks.OnItemClickListener;
import com.thoughtworks.todo.databinding.ItemTodoBinding;
import com.thoughtworks.todo.todo.model.Todo;
import com.thoughtworks.todo.utils.RecyclerViewHolder;

import java.util.Date;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Todo> todoList;
    private OnItemClickListener<Todo> onClickListener;
    private boolean isPendingList;

    public TodoListAdapter(List<Todo> todoList, OnItemClickListener<Todo> onClickListener, boolean isPendingList) {

        this.todoList = todoList;
        this.onClickListener = onClickListener;
        this.isPendingList = isPendingList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTodoBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_todo, parent, false);
        return new RecyclerViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        ItemTodoBinding binding = (ItemTodoBinding) viewHolder.getBinding();
        Todo todo = todoList.get(position);
        if (isPendingList) {
            binding.checkbox.setChecked(false);
            binding.checkbox.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b)
                    onClickListener.onItemClick(todo, compoundButton, position);
            });
            binding.getRoot().setOnClickListener(v -> onClickListener.onItemClick(todo, v, position));
        } else {
            binding.checkbox.setChecked(true);
        }
        binding.setVariable(BR.item, todo);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
