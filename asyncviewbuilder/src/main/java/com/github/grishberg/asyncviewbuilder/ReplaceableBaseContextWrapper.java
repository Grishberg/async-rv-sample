package com.github.grishberg.asyncviewbuilder;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * Allows to replace base context with new one.
 */
public class ReplaceableBaseContextWrapper extends ContextWrapper {
    public ReplaceableBaseContextWrapper(Context base) {
        super(base);
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
