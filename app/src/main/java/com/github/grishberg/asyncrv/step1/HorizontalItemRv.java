package com.github.grishberg.asyncrv.step1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

class HorizontalItemRv extends RecyclerView {
    public HorizontalItemRv(Context context) {
        this(context, null);
    }

    public HorizontalItemRv(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalItemRv(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }
}
