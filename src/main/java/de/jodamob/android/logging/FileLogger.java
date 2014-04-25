package de.jodamob.android.logging;

import java.io.File;
import java.util.logging.Logger;
import android.content.Context;

public class FileLogger extends RedirectedLogging {

    static final String FOLDER_NAME = "logs";
    static final String FILE_PATTERN = "applog";
    static final String FILE_EXTENSION = ".log";
    
    private final FileLoggerPreparation fileCreator;
    private final Logger logger = Logger.getLogger("");

    public FileLogger(Context context) {
        this(context, new SilentLogger());
    }
    
    public static File getLogFileContent(Context context) {
        return new FileLoggerCollector(context).getAsSingleLogfile();
    }
    
    public FileLogger(final Context context, final Logging redirectLog) {
        super(redirectLog);
        this.fileCreator = new FileLoggerPreparation(context);
        prepareInBackground();
    }

    @Override
    public int i(String tag, String message) {
        logger.info(message);
        return super.i(tag, message);
    }

    @Override
    public  int i(String tag, String message, Throwable tr) {
        logger.info(message + Log.getStackTraceString(tr));
        return super.i(tag, message, tr);
    }

    @Override
    public  int w(String tag, String message) {
        logger.warning(message);
        return super.w(tag, message);
    }

    @Override
    public  int w(String tag, String message, Throwable tr) {
        logger.warning(message + Log.getStackTraceString(tr));
        return super.w(tag, message, tr);
    }

    @Override
    public  int w(String tag, Throwable tr) {
        logger.warning(tag + Log.getStackTraceString(tr));
        return super.w(tag, tr);
    }

    @Override
    public  int e(String tag, String message) {
        logger.severe(message);
        return super.e(tag, message);
    }

    @Override
    public  int e(String tag, String message, Throwable tr) {
        logger.severe(message + getStackTraceString(tr));
        return super.e(tag, message, tr);
    }

    @Override
    public  int e(String message) {
        logger.severe(message);
        return super.e(message);
    }

    @Override
    public  int e(String msg, Throwable tr) {
        logger.severe(msg + getStackTraceString(tr)); 
        return super.e(msg, tr);
    }

    @Override
    public  int wtf(String tag, String message, Throwable tr) {
        logger.severe(message + getStackTraceString(tr));
        return super.wtf(tag, message, tr);
    }

    @Override
    public  int wtf(String tag, Throwable tr) {
        logger.severe(tag + getStackTraceString(tr));
        return super.wtf(tag, tr);
    }

    @Override
    public  int wtf(String tag, String message) {
        logger.severe(message);
        return super.wtf(tag, message);
    }

    @Override
    public  int wtf(Throwable tr) {
        logger.severe(getStackTraceString(tr));
        return super.wtf(tr);
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
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
