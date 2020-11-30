package com.github.grishberg.asyncrv.step1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.grishberg.asyncviewbuilder.ViewHolderProvider;

import java.util.ArrayList;
import java.util.List;

class Step1ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
    private final ViewHolderProvider<ItemsViewHolder> viewHolderProvider;
    private final ArrayList<Item> items = new ArrayList<>();

    Step1ItemsAdapter(ViewHolderProvider<ItemsViewHolder> viewHolderProvider) {
        this.viewHolderProvider = viewHolderProvider;
    }

    public void populate(List<Item> i) {
        items.clear();
        items.addAll(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewHolderProvider.getViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
