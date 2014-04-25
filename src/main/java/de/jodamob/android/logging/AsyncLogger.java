package de.jodamob.android.logging;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncLogger extends RedirectedLogging {

    private final ExecutorService background;

    public AsyncLogger(Logging redirectLog) {
        super(redirectLog);
        this.background = Executors.newSingleThreadExecutor();
    }

    @Override
    public  int v(final String tag, final String message) {
        background.execute(new Runnable() {
            
            @Override
            public void run() {
                AsyncLogger.super.v(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int v(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.v(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int d(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.d(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int d(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.d(tag, message, tr);
            }
        });
        return 0;
    }

    
    @Override
    public  int i(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.i(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int i(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.i(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int w(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.w(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int w(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.w(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int w(final String tag, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.w(tag, tr);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.e(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.e(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.e(message);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String msg, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.e(msg, tr);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.wtf(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final String tag, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.wtf(tag, tr);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.wtf(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                AsyncLogger.super.wtf(tr);
            }
        });
        return 0;
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return AsyncLogger.super.getStackTraceString(tr);
    }
}
