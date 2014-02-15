package de.jodamob.android.logging;

import java.io.File;
import java.util.logging.Logger;
import android.content.Context;

public class FileLogger implements Logging {

    static final String FOLDER_NAME = "logs";
    static final String FILE_PATTERN = "applog";
    static final String FILE_EXTENSION = ".log";
    
    private final Logging redirectLog;
    private final FileLoggerPreparation fileCreator;
    private final Logger logger = Logger.getLogger("");

    public FileLogger(Context context) {
        this(context, new SilentLogger());
    }
    
    public static File getLogFileContent(Context context) {
        return new FileLoggerCollector(context).getAsSingleLogfile();
    }
    
    public FileLogger(final Context context, final Logging redirectLog) {
        this.fileCreator = new FileLoggerPreparation(context);
        this.redirectLog = redirectLog;
        prepareInBackground();
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
        logger.info(message);
        return redirectLog.i(tag, message);
    }

    @Override
    public  int i(String tag, String message, Throwable tr) {
        logger.info(message + Log.getStackTraceString(tr));
        return redirectLog.i(tag, message, tr);
    }

    @Override
    public  int w(String tag, String message) {
        logger.warning(message);
        return redirectLog.w(tag, message);
    }

    @Override
    public  int w(String tag, String message, Throwable tr) {
        logger.warning(message + Log.getStackTraceString(tr));
        return redirectLog.w(tag, message, tr);
    }

    @Override
    public  int w(String tag, Throwable tr) {
        logger.warning(tag + Log.getStackTraceString(tr));
        return redirectLog.w(tag, tr);
    }

    @Override
    public  int e(String tag, String message) {
        logger.severe(message);
        return redirectLog.e(tag, message);
    }

    @Override
    public  int e(String tag, String message, Throwable tr) {
        logger.severe(message + getStackTraceString(tr));
        return redirectLog.e(tag, message, tr);
    }

    @Override
    public  int e(String message) {
        logger.severe(message);
        return redirectLog.e(message);
    }

    @Override
    public  int e(String msg, Throwable tr) {
        logger.severe(msg + getStackTraceString(tr)); 
        return redirectLog.e(msg, tr);
    }

    @Override
    public  int wtf(String tag, String message, Throwable tr) {
        logger.severe(message + getStackTraceString(tr));
        return redirectLog.wtf(tag, message, tr);
    }

    @Override
    public  int wtf(String tag, Throwable tr) {
        logger.severe(tag + getStackTraceString(tr));
        return redirectLog.wtf(tag, tr);
    }

    @Override
    public  int wtf(String tag, String message) {
        logger.severe(message);
        return redirectLog.wtf(tag, message);
    }

    @Override
    public  int wtf(Throwable tr) {
        logger.severe(getStackTraceString(tr));
        return redirectLog.wtf(tr);
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return redirectLog.getStackTraceString(tr);
    }
    
    private void prepareInBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fileCreator.prepare(logger);
            }
        }).start();
    }
}
