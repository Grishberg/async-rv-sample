package com.github.grishberg.asyncrv.common;

import android.os.Debug;

public class TraceRecorder {
    private TraceRecorder(){/* not used */}
    private static boolean sIsRecording;

    public static void startRecording() {
        if (sIsRecording) {
            return;
        }
        sIsRecording = true;
        Debug.startMethodTracing("traces");
    }

    public static void stopRecording() {
        if (!sIsRecording) {
            return;
        }
        Debug.stopMethodTracing();
    }
}
