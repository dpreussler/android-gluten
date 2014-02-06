package de.jodamob.android.logging;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.content.Context;

public class AsyncLogger implements _Log {

    private final _Log redirectLog;
    private final ExecutorService background;

    public AsyncLogger(Context context, _Log redirectLog) {
        this.redirectLog = redirectLog;
        this.background = Executors.newSingleThreadExecutor();
    }

    @Override
    public  int v(final String tag, final String message) {
        background.execute(new Runnable() {
            
            @Override
            public void run() {
                redirectLog.v(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int v(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.v(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int d(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.d(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int d(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.d(tag, message, tr);
            }
        });
        return 0;
    }

    
    @Override
    public  int i(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.i(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int i(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.i(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int w(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.w(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int w(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.w(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int w(final String tag, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.w(tag, tr);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.e(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.e(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.e(message);
            }
        });
        return 0;
    }

    @Override
    public  int e(final String msg, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.e(msg, tr);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final String tag, final String message, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.wtf(tag, message, tr);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final String tag, final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.wtf(tag, tr);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final String tag, final String message) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.wtf(tag, message);
            }
        });
        return 0;
    }

    @Override
    public  int wtf(final Throwable tr) {
        background.execute(new Runnable() {
            @Override
            public void run() {
                redirectLog.wtf(tr);
            }
        });
        return 0;
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return redirectLog.getStackTraceString(tr);
    }
}
