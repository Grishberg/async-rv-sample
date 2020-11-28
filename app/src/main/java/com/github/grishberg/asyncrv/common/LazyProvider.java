package com.github.grishberg.asyncrv.common;

import javax.inject.Provider;

import androidx.annotation.Nullable;

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
