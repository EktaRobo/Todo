package com.thoughtworks.todo.utils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static dagger.internal.Preconditions.checkNotNull;

public class FragmentHelper {

    public static void clearBackStack(final FragmentActivity activity) {
        try {
            activity.getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (IllegalStateException e) {
            // e.printStackTrace(); // There's no way to avoid getting this if saveInstanceState has already been called.
        }

    }

    public static int getStackCount(final FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        return fragmentManager.getBackStackEntryCount();
    }

    @Nullable
    public static Fragment getFragment(final FragmentActivity activity, final int containerId) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        return fragmentManager.findFragmentById(containerId);
    }

    @Nullable
    public static Fragment getFragment(final FragmentActivity activity, final String TAG) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        return fragmentManager.findFragmentByTag(TAG);
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void replaceFragment(@NonNull FragmentActivity activity, int container, @NonNull Fragment fragment) {
        checkNotNull(activity);
        checkNotNull(fragment);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void replaceFragment(@NonNull FragmentActivity activity, int container, @NonNull Fragment fragment, String tag) {
        checkNotNull(activity);
        checkNotNull(fragment);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);

        if (!fragmentPopped && fragmentManager.findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(container, fragment, tag);
            transaction.addToBackStack(tag);
            transaction.commit();
        }
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragment(@NonNull FragmentActivity activity, int container, @NonNull Fragment fragment) {
        checkNotNull(activity);
        checkNotNull(fragment);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(container, fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragment(@NonNull FragmentActivity activity, int container, @NonNull Fragment fragment, String tag) {
        checkNotNull(activity);
        checkNotNull(fragment);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(container, fragment);
        transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }
}
