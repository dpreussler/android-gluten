package de.jodamob.android.logging;

/**
 * interface version of Android.util.Log methods
 */
public interface Logging {

    int v(String tag, String message);

    int v(String tag, String message, Throwable tr);

    int d(String tag, String message);

    int d(String tag, String message, Throwable tr);

    int i(String tag, String message);

    int i(String tag, String message, Throwable tr);

    int w(String tag, String message);

    int w(String tag, String message, Throwable tr);

    int w(String tag, Throwable tr);

    int e(String tag, String message);

    int e(String tag, String message, Throwable tr);

    int e(String message);

    int e(String msg, Throwable tr);

    int wtf(String tag, String message, Throwable tr);

    int wtf(String tag, Throwable tr);

    int wtf(String tag, String message);

    int wtf(Throwable tr);

    int logStackTrace(String message);

    String getStackTraceString(Throwable tr);

    int e(Throwable tr);
}