package de.jodamob.android.logging;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * redirects logs plus writes uncaught exceptions as errors
 */
public class UncaughtExceptionLogger extends RedirectedLogging  {

    private final Logging redirectLog;

    public UncaughtExceptionLogger(Logging redirectLog) {
        super(redirectLog);
        this.redirectLog = redirectLog;
        registerAsExceptionHandler();
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
