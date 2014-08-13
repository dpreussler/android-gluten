package de.jodamob.android.utils;

import java.io.Closeable;
import de.jodamob.android.logging.Log;

public final class Closeables {

    public static void closeQuietly(final Closeable... closeables) {
        for(Closeable closeable : closeables) {
            closeQuietly(closeable);
        }
    }

    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception throwable) {
            Log.e("close stream error", throwable);
        }
    }

    private Closeables() {
    };
}