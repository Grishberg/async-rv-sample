package com.github.grishberg.asyncrv.step1;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.MainThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.github.grishberg.asyncrv.R;
import com.github.grishberg.asyncviewbuilder.DimensionProvider;
import com.github.grishberg.asyncviewbuilder.ReplaceableBaseContextWrapper;
import com.github.grishberg.asyncviewbuilder.ViewHolderProvider;
import com.github.grishberg.asyncviewbuilder.ViewProvider;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Singleton;

@Singleton
public class Step1ViewProvider implements ViewProvider<RecyclerView> {
    private final Application appContext;
    private final ReplaceableBaseContextWrapper contextWrapper;
    private final Future<RecyclerView> viewFuture;
    private final List<Item> items;
    private final DimensionProvider viewDimensionProvider;

    public Step1ViewProvider(Application appContext,
                             ExecutorService executor,
                             List<Item> items,
                             DimensionProvider viewDimensionProvider) {
        this.appContext = appContext;
        contextWrapper = new ReplaceableBaseContextWrapper(appContext);
        viewFuture = executor.submit(new PrepareRecyclerViewTask());
        this.items = items;
        this.viewDimensionProvider = viewDimensionProvider;
    }

    @MainThread
    public void onActivityCreated(Activity activity) {
        contextWrapper.attachBaseContext(activity);
    }

    @MainThread
    public void onActivityDestroyed() {
        contextWrapper.attachBaseContext(appContext);
    }

    @Override
    public RecyclerView getView() {
        try {
            return viewFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            // TODO: return empty view;
            throw new IllegalStateException("Interrupted");
        }
    }

    private class PrepareRecyclerViewTask implements Callable<RecyclerView> {
        @Override
        public RecyclerView call() throws Exception {
            final HorizontalItemRv rv = new HorizontalItemRv(contextWrapper, null, R.style.RecyclerView);
            rv.setLayoutParams(new ViewGroup.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));

            rv.setLayoutManager(new LinearLayoutManager(contextWrapper,
                    LinearLayoutManager.HORIZONTAL, false));

            if (!items.isEmpty()) {
                ViewHolderProviderImpl viewHolderProvider =
                        new ViewHolderProviderImpl(LayoutInflater.from(contextWrapper));
                Step1ItemsAdapter adapter = new Step1ItemsAdapter(viewHolderProvider);
                rv.setAdapter(adapter);
                adapter.populate(items);
            }
            measureView(rv);
            return rv;
        }

        private void measureView(HorizontalItemRv v) {
            v.measure(
                    View.MeasureSpec.makeMeasureSpec(viewDimensionProvider.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(viewDimensionProvider.getHeight(), View.MeasureSpec.EXACTLY));
            v.performLayout(true, 0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        }
    }

    private class ViewHolderProviderImpl implements ViewHolderProvider<ItemsViewHolder> {
        private final LayoutInflater inflater;

        private ViewHolderProviderImpl(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public ItemsViewHolder getViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.step_1_item_layout, null, false);
            return new Step1ItemsViewHolder(view);
        }
    }
}
