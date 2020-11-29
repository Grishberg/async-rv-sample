package com.github.grishberg.asyncrv.step1;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.support.annotation.MainThread;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.github.grishberg.asyncrv.R;
import com.github.grishberg.asyncviewbuilder.ReplaceableBaseContextWrapper;
import com.github.grishberg.asyncviewbuilder.ViewProvider;

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

    public Step1ViewProvider(Application appContext, ExecutorService executor) {
        this.appContext = appContext;
        contextWrapper = new ReplaceableBaseContextWrapper(appContext);
        viewFuture = executor.submit(new PrepareRecyclerViewTask());
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

            measureView(rv);
            return rv;
        }

        private void measureView(RecyclerView rv) {

        }
    }
}
