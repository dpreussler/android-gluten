package de.jodamob.android.logging;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * redirects logs plus writes uncaught exceptions as errors
 */
public class UncaughtExceptionLogger implements Logging {

    private final Logging redirectLog;

    public UncaughtExceptionLogger(Logging redirectLog) {
        this.redirectLog = redirectLog;
        registerAsExceptionHandler();
    }

    @Override
    public  int v(String tag, String message) {
        return redirectLog.v(tag, message);
    }

    @Override
    public  int v(String tag, String message, Throwable tr) {
        return redirectLog.v(tag, message, tr);
    }

    @Override
    public  int d(String tag, String message) {
        return redirectLog.d(tag, message);
    }

    @Override
    public  int d(String tag, String message, Throwable tr) {
        return redirectLog.d(tag, message, tr);
    }

    @Override
    public  int i(String tag, String message) {
        return redirectLog.i(tag, message);
    }

    @Override
    public  int i(String tag, String message, Throwable tr) {
        return redirectLog.i(tag, message, tr);
    }

    @Override
    public  int w(String tag, String message) {
        return redirectLog.w(tag, message);
    }

    @Override
    public  int w(String tag, String message, Throwable tr) {
        return redirectLog.w(tag, message, tr);
    }

    @Override
    public  int w(String tag, Throwable tr) {
        return redirectLog.w(tag, tr);
    }

    @Override
    public  int e(String tag, String message) {
        return redirectLog.e(tag, message);
    }

    @Override
    public  int e(String tag, String message, Throwable tr) {
        return redirectLog.e(tag, message, tr);
    }

    @Override
    public  int e(String message) {
        return redirectLog.e(message);
    }

    @Override
    public  int e(String msg, Throwable tr) {
        return redirectLog.e(msg, tr);
    }

    @Override
    public  int wtf(String tag, String message, Throwable tr) {
        return redirectLog.wtf(tag, message, tr);
    }

    @Override
    public  int wtf(String tag, Throwable tr) {
        return redirectLog.wtf(tag, tr);
    }

    @Override
    public  int wtf(String tag, String message) {
        return redirectLog.wtf(tag, message);
    }

    @Override
    public  int wtf(Throwable tr) {
        return redirectLog.wtf(tr);
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return redirectLog.getStackTraceString(tr);
    }
    
    private void registerAsExceptionHandler() {
        UncaughtExceptionHandler hdl = Thread.getDefaultUncaughtExceptionHandler();
        if (!(hdl instanceof HandlerImplementation)) {
            Thread.setDefaultUncaughtExceptionHandler(new HandlerImplementation(hdl, redirectLog));
        }
    }

    private static class HandlerImplementation implements UncaughtExceptionHandler {

        private UncaughtExceptionHandler handler;
        private Logging log;

        public HandlerImplementation(UncaughtExceptionHandler hdl, Logging log) {
            this.handler = hdl;
            this.log = log;
        }

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            log.e("unexpected error", ex);
            handler.uncaughtException(thread, ex);
        }
    }
}
