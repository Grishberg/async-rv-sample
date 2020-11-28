package com.github.grishberg.asyncviewbuilder;

import android.view.View;

/**
 * Provides view.
 */
public interface ViewProvider<T extends View> {
    T getView();
}
