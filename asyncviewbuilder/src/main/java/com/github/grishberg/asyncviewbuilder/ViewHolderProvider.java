package com.github.grishberg.asyncviewbuilder;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface ViewHolderProvider<T extends RecyclerView.ViewHolder> {
    T getViewHolder(ViewGroup parent, int viewType);
}
