package com.github.grishberg.asyncrv;

import android.app.Application;
import android.support.annotation.MainThread;
import android.support.v7.widget.RecyclerView;

import com.github.grishberg.asyncrv.step1.Step1ViewProvider;
import com.github.grishberg.asyncviewbuilder.ViewProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App extends Application {
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    // Sets the amount of time an idle thread waits before terminating
    private static final long KEEP_ALIVE_TIME = 1000L;
    // Sets the Time Unit to Milliseconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.MILLISECONDS;

    private static Step1ViewProvider sStep1ViewProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        sStep1ViewProvider = new Step1ViewProvider(this, createExecutorService());
    }

    private ExecutorService createExecutorService() {
        return new ThreadPoolExecutor(
                NUMBER_OF_CORES + 5,  // Initial pool size
                NUMBER_OF_CORES + 8,  // Max pool size
                KEEP_ALIVE_TIME,  // Time idle thread waits before terminating
                KEEP_ALIVE_TIME_UNIT,  // Sets the Time Unit for KEEP_ALIVE_TIME
                new LinkedBlockingDeque<>()
        );
    }

    @MainThread
    public static ViewProvider<RecyclerView> step1Provider() {
        return sStep1ViewProvider;
    }
}
