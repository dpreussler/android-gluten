package de.jodamob.android.logging;


public class LogCatLogger implements _Log {

    private static final String TAG = "";

    @Override
    public  int v(String tag, String message) {
        return android.util.Log.v(tag, message);
    }

    @Override
    public  int v(String tag, String message, Throwable tr) {
        return android.util.Log.v(tag, message, tr);
    }

    @Override
    public  int d(String tag, String message) {
        return android.util.Log.d(tag, message);
    }

    @Override
    public  int d(String tag, String message, Throwable tr) {
        return android.util.Log.d(tag, message, tr);
    }

    @Override
    public  int i(String tag, String message) {
        return android.util.Log.i(tag, message);
    }

    @Override
    public  int i(String tag, String message, Throwable tr) {
        return android.util.Log.i(tag, message, tr);
    }

    @Override
    public  int w(String tag, String message) {
        return android.util.Log.w(tag, message);
    }

    @Override
    public  int w(String tag, String message, Throwable tr) {
        return android.util.Log.w(tag, message, tr);
    }

    @Override
    public  int w(String tag, Throwable tr) {
        return android.util.Log.w(tag, tr);
    }

    @Override
    public  int e(String tag, String message) {
        return android.util.Log.e(tag, message);
    }

    @Override
    public  int e(String tag, String message, Throwable tr) {
        return android.util.Log.e(tag, message, tr);
    }

    @Override
    public  int e(String message) {
        return android.util.Log.e(TAG, message);
    }

    @Override
    public  int e(String msg, Exception tr) {
        return android.util.Log.e(TAG, msg, tr);
    }

    @Override
    public  int wtf(String tag, String message, Throwable tr) {
        return android.util.Log.wtf(tag, message, tr);
    }

    @Override
    public  int wtf(String tag, Exception tr) {
        return android.util.Log.wtf(tag, tr);
    }

    @Override
    public  int wtf(String tag, String message) {
        return android.util.Log.wtf(tag, message);
    }

    @Override
    public  int wtf(Throwable tr) {
        return android.util.Log.wtf(TAG, tr);
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }
}
