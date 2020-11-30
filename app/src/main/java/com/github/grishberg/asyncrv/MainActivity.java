package com.github.grishberg.asyncrv;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.grishberg.asyncrv.common.TraceRecorder;
import com.github.grishberg.asyncviewbuilder.ViewProvider;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup content = findViewById(R.id.content);
        content.setBackgroundColor(Color.GREEN);

        ViewProvider<RecyclerView> viewProvider = App.step1Provider();
        content.addView(viewProvider.getView());
    }

    @Override
    protected void onPause() {
        super.onPause();
        TraceRecorder.stopRecording();
    }
}