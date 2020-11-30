package com.github.grishberg.asyncrv.step1;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.github.grishberg.asyncviewbuilder.DimensionProvider;

public class ScreenDimensionProvider implements DimensionProvider {
    private final Point size = new Point();

    public ScreenDimensionProvider(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        display.getSize(size);
    }

    @Override
    public int getWidth() {
        return size.x;
    }

    @Override
    public int getHeight() {
        return size.y;
    }
}
