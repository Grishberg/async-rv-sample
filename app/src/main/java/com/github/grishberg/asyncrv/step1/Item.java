package com.github.grishberg.asyncrv.step1;

import android.support.annotation.DrawableRes;

public class Item {
    final String text;
    @DrawableRes
    final int iconRes;

    public Item(String text, @DrawableRes int iconRes) {
        this.text = text;
        this.iconRes = iconRes;
    }
}
