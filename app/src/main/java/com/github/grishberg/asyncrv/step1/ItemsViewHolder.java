package com.github.grishberg.asyncrv.step1;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ItemsViewHolder extends RecyclerView.ViewHolder {
    public ItemsViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Item i);
}
