package de.jodamob.android.logging;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * default exception handler
 */
class LoggingUncaughtExceptionHandler {

    LoggingUncaughtExceptionHandler() {
    }
    
    void register() {
        UncaughtExceptionHandler hdl = Thread.getDefaultUncaughtExceptionHandler();
        if (hdl instanceof HandlerImplementation) {
            // already done
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new HandlerImplementation(hdl));
    }

    private static class HandlerImplementation implements UncaughtExceptionHandler {
        private final UncaughtExceptionHandler existingHandler;

        public HandlerImplementation(UncaughtExceptionHandler handler) {
            existingHandler = handler;
        }

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.wtf(ex);
            if (!(existingHandler instanceof HandlerImplementation)) {
                // double check to prevent stackoverflow
                existingHandler.uncaughtException(thread, ex);
            }
        }
    }
}
