package com.thoughtworks.todo.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;


public class AppExecutors {

    private final Executor disk;

    private final Executor network;

    private final Executor mainThread;

    public AppExecutors(Executor disk, Executor network, Executor mainThread) {
        this.disk = disk;
        this.network = network;
        this.mainThread = mainThread;
    }

    @Inject
    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                new MainThreadExecutor());
    }

    public Executor disk() {
        return disk;
    }

    public Executor network() {
        return network;
    }

    public Executor mainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
