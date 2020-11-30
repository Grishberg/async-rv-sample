package com.github.grishberg.asyncrv.step1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

class HorizontalItemRv extends RecyclerView {
    private boolean useCashedDimensions = true;

    public HorizontalItemRv(Context context) {
        this(context, null);
    }

    public HorizontalItemRv(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalItemRv(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                useCashedDimensions = false;
                return true;
            }
        });
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if (useCashedDimensions && getMeasuredWidth() > 0 && getMeasuredHeight() > 0) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        super.onMeasure(widthSpec, heightSpec);
    }

    public void performLayout(boolean changed, int l, int t, int r, int b) {
        onLayout(changed, l, t, r, b);
    }
}
