package de.jodamob.android.logging;

public class LogCatLogger implements Logging {

    private static final String TAG = "";
    private final Logging redirectLog;

    public LogCatLogger() {
        this(new SilentLogger());
    }

    public LogCatLogger(Logging redirectLog) {
        this.redirectLog = redirectLog;
    }

    @Override
    public int v(String tag, String message) {
        redirectLog.v(tag, message);
        return android.util.Log.v(tag, message);
    }

    @Override
    public int v(String tag, String message, Throwable tr) {
        redirectLog.v(tag, message, tr);
        return android.util.Log.v(tag, message, tr);
    }

    @Override
    public int d(String tag, String message) {
        redirectLog.d(tag, message);
        return android.util.Log.d(tag, message);
    }

    @Override
    public int d(String tag, String message, Throwable tr) {
        redirectLog.d(tag, message, tr);
        return android.util.Log.d(tag, message, tr);
    }

    @Override
    public int i(String tag, String message) {
        redirectLog.i(tag, message);
        return android.util.Log.i(tag, message);
    }

    @Override
    public int i(String tag, String message, Throwable tr) {
        redirectLog.i(tag, message, tr);
        return android.util.Log.i(tag, message, tr);
    }

    @Override
    public int w(String tag, String message) {
        redirectLog.w(tag, message);
        return android.util.Log.w(tag, message);
    }

    @Override
    public int w(String tag, String message, Throwable tr) {
        redirectLog.w(tag, message, tr);
        return android.util.Log.w(tag, message, tr);
    }

    @Override
    public int w(String tag, Throwable tr) {
        redirectLog.w(tag, tr);
        return android.util.Log.w(tag, tr);
    }

    @Override
    public int e(String tag, String message) {
        redirectLog.e(tag, message);
        return android.util.Log.e(tag, message);
    }

    @Override
    public int e(String tag, String message, Throwable tr) {
        redirectLog.e(tag, message, tr);
        return android.util.Log.e(tag, message, tr);
    }

    @Override
    public int e(String message) {
        redirectLog.e(message);
        return android.util.Log.e(TAG, message);
    }

    @Override
    public int e(String msg, Throwable tr) {
        redirectLog.e(msg, tr);
        return android.util.Log.e(TAG, msg, tr);
    }

    @Override
    public int wtf(String tag, String message, Throwable tr) {
        redirectLog.wtf(tag, message, tr);
        return android.util.Log.wtf(tag, message, tr);
    }

    @Override
    public int wtf(String tag, Throwable tr) {
        redirectLog.wtf(tag, tr);
        return android.util.Log.wtf(tag, tr);
    }

    @Override
    public int wtf(String tag, String message) {
        redirectLog.wtf(tag, message);
        return android.util.Log.wtf(tag, message);
    }

    @Override
    public int wtf(Throwable tr) {
        redirectLog.wtf(tr);
        return android.util.Log.wtf(TAG, tr);
    }

    @Override
    public String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }
}
