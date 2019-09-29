package com.thoughtworks.todo.common.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;



public abstract class BaseViewModel extends AndroidViewModel {

    public final ObservableBoolean loading = new ObservableBoolean(false);

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public void setLoading(boolean loading) {
        this.loading.set(loading);
    }

}
