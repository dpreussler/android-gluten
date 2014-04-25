package de.jodamob.android.logging;

public class LogCatLogger extends RedirectedLogging {

    private static final String TAG = "";

    public LogCatLogger() {
        this(new SilentLogger());
    }

    public LogCatLogger(Logging redirectLog) {
        super(redirectLog);
    }

    @Override
    public int v(String tag, String message) {
        super.v(tag, message);
        return android.util.Log.v(tag, message);
    }

    @Override
    public int v(String tag, String message, Throwable tr) {
        super.v(tag, message, tr);
        return android.util.Log.v(tag, message, tr);
    }

    @Override
    public int d(String tag, String message) {
        super.d(tag, message);
        return android.util.Log.d(tag, message);
    }

    @Override
    public int d(String tag, String message, Throwable tr) {
        super.d(tag, message, tr);
        return android.util.Log.d(tag, message, tr);
    }

    @Override
    public int i(String tag, String message) {
        super.i(tag, message);
        return android.util.Log.i(tag, message);
    }

    @Override
    public int i(String tag, String message, Throwable tr) {
        super.i(tag, message, tr);
        return android.util.Log.i(tag, message, tr);
    }

    @Override
    public int w(String tag, String message) {
        super.w(tag, message);
        return android.util.Log.w(tag, message);
    }

    @Override
    public int w(String tag, String message, Throwable tr) {
        super.w(tag, message, tr);
        return android.util.Log.w(tag, message, tr);
    }

    @Override
    public int w(String tag, Throwable tr) {
        super.w(tag, tr);
        return android.util.Log.w(tag, tr);
    }

    @Override
    public int e(String tag, String message) {
        super.e(tag, message);
        return android.util.Log.e(tag, message);
    }

    @Override
    public int e(String tag, String message, Throwable tr) {
        super.e(tag, message, tr);
        return android.util.Log.e(tag, message, tr);
    }

    @Override
    public int e(String message) {
        super.e(message);
        return android.util.Log.e(TAG, message);
    }

    @Override
    public int e(String msg, Throwable tr) {
        super.e(msg, tr);
        return android.util.Log.e(TAG, msg, tr);
    }

    @Override
    public int e(Throwable tr) {
        super.e(tr);
        return android.util.Log.e(TAG, "error", tr);
    }

    @Override
    public int wtf(String tag, String message, Throwable tr) {
        super.wtf(tag, message, tr);
        return android.util.Log.wtf(tag, message, tr);
    }

    @Override
    public int wtf(String tag, Throwable tr) {
        super.wtf(tag, tr);
        return android.util.Log.wtf(tag, tr);
    }

    @Override
    public int wtf(String tag, String message) {
        super.wtf(tag, message);
        return android.util.Log.wtf(tag, message);
    }

    @Override
    public int wtf(Throwable tr) {
        super.wtf(tr);
        return android.util.Log.wtf(TAG, tr);
    }

    @Override
    public int logStackTrace(String message) {
        super.logStackTrace(message);
        return android.util.Log.e(message, getStackTraceString(new Throwable().fillInStackTrace()));
    }

    @Override
    public String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }

}
