package com.thoughtworks.todo.common.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thoughtworks.todo.R;
import com.thoughtworks.todo.common.callbacks.ActivityListener;
import com.thoughtworks.todo.databinding.ActivityBaseBinding;
import com.thoughtworks.todo.utils.FragmentHelper;

public abstract class BaseActivity extends AppCompatActivity implements ActivityListener {
    private ActivityBaseBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(createBundleNoFragmentRestore(savedInstanceState));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        setSupportActionBar(binding.toolbar);
    }

    protected void addView(View view) {
        binding.content.addView(view);
    }

    public void showToolBar() {
        binding.toolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolBar() {
        binding.toolbar.setVisibility(View.GONE);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }


    @Override
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (IllegalStateException e) {
            // this exception is thrown when onBackPressed() is explicitly called by programmer when activity is in paused state.
        }
    }

    /**
     * Improve bundle to prevent restoring of fragments.
     *
     * @param bundle bundle container
     * @return improved bundle with removed "fragments parcelable"
     */
    private static Bundle createBundleNoFragmentRestore(Bundle bundle) {
        if (bundle != null) {
            bundle.remove("android:fragments");
        }
        return bundle;
    }

    @Override
    public void addFragment(Fragment fragment) {
        FragmentHelper.addFragment(this, R.id.content, fragment);
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentHelper.replaceFragment(this, R.id.content, fragment);
    }

    @Override
    public void addFragment(Fragment fragment, String tag) {
        FragmentHelper.addFragment(this, R.id.content, fragment, tag);
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        FragmentHelper.replaceFragment(this, R.id.content, fragment, tag);

    }

    public Fragment getFragment() {
        return FragmentHelper.getFragment(this, R.id.content);
    }

}
