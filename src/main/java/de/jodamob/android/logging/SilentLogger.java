package de.jodamob.android.logging;

/**
 * this logger logs nothing:
 * enjoy the silence
 */
public class SilentLogger implements Logging {

    public SilentLogger() {
    }

    @Override
    public int v(String tag, String message) {
        return 0;
    }

    @Override
    public int v(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int d(String tag, String message) {
        return 0;
    }

    @Override
    public int d(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int i(String tag, String message) {
        return 0;
    }

    @Override
    public int i(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int w(String tag, String message) {
        return 0;
    }

    @Override
    public int w(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int w(String tag, Throwable tr) {
        return 0;
    }

    @Override
    public int e(String tag, String message) {
        return 0;
    }

    @Override
    public int e(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int e(String message) {
        return 0;
    }

    @Override
    public int e(String msg, Throwable tr) {
        return 0;
    }

    @Override
    public int wtf(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int wtf(String tag, Throwable tr) {
        return 0;
    }

    @Override
    public int wtf(String tag, String message) {
        return 0;
    }

    @Override
    public int wtf(Throwable tr) {
        return 0;
    }

    @Override
    public String getStackTraceString(Throwable tr) {
        return null;
    }
}
