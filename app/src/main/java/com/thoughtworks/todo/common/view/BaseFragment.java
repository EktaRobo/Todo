package com.thoughtworks.todo.common.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;
import com.thoughtworks.todo.R;
import com.thoughtworks.todo.common.callbacks.ActivityListener;
import com.thoughtworks.todo.common.callbacks.BaseNavigator;
import com.thoughtworks.todo.databinding.FragmentBaseBinding;
import com.thoughtworks.todo.utils.Constants;
import com.thoughtworks.todo.utils.Network;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link DaggerFragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements BaseNavigator {

    private FragmentBaseBinding binding;
    protected ActivityListener activityListener;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityListener = (ActivityListener) context;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    protected View addView(View view) {
        binding.baseContainer.addView(view);
        return binding.getRoot();
    }

    protected boolean isLastItem(RecyclerView recyclerView) {
        if (!Network.isConnected(getActivity())) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = recyclerView.getChildCount();
        int firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        boolean isLastItem = layoutManager.getItemCount() == firstVisibleItem + visibleItemCount;
        return isLastItem;
    }

    @Nullable
    public String getToolbarTitle() {
        if (getArguments() != null) {
            return getArguments().getString(Constants.TOOLBAR_TITLE);
        } else return null;
    }


    public boolean isProgressbarShowing() {
        return binding.progressBar.getVisibility() == View.VISIBLE;
    }

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setToolbarTitle(int resId) {
        getActivity().setTitle(resId);
    }

    protected void showSnackBar(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}
