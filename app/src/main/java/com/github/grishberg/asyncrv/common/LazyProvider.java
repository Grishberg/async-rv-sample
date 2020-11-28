package com.github.grishberg.asyncrv.common;

import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

import javax.inject.Provider;

@MainThread
public abstract class LazyProvider<T> implements Provider<T> {
    @Nullable
    private T value;

    @Override
    public T get() {
        if (value == null) {
            value = create();
        }
        return value;
    }

    protected abstract T create();
}
