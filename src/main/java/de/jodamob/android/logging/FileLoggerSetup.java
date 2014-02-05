package de.jodamob.android.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import android.content.Context;
import android.util.Log;

class FileLoggerSetup {
    
    private static final String FOLDER_NAME = "logs";
    private static final String FILE_PATTERN = "applog";
    private static final String FILE_EXTENSION = ".log";
    private final Context context;

    FileLoggerSetup(Context context) {
        this.context = context;
    }
    
    void prepare(Logger logger) {
        prepare(logger,  new LoggingUncaughtExceptionHandler());
    }
    
    void prepare(Logger logger, LoggingUncaughtExceptionHandler excHandler) {
        prepare(logger, getLogDir(), FILE_PATTERN, FILE_EXTENSION);
        excHandler.register();
    }
    
    private File getLogDir() {
        return new File(context.getExternalCacheDir(), FOLDER_NAME);
    }

    private void prepare(Logger logger, File logFolder, String logpattern, String logExtention) {
        removeExistingHandlers(logger);
        enableFileLogger(logger, logFolder, logpattern, logExtention);
    }

    private void enableFileLogger(Logger logger, File logFolder, String logpattern, String logExtention) {
        try {
            setLoggerToFile(logger, logFolder, logpattern, logExtention);    
        } catch(IOException e) {
            Log.wtf("LOG", e);
        }
    }

    private void removeExistingHandlers(Logger logger) {
        for (final Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
    }

    private void setLoggerToFile(Logger logger, File logFolder, String logpattern, String logExtention) throws IOException {
        createLogFolder(logFolder);
        createLogHandler(logger, logFolder, logpattern, logExtention);
    }

    private void createLogHandler(Logger logger, File logFolder, String logpattern, String logExtention) throws IOException {
        logger.addHandler(new SimpleFileHandler(logFolder, logpattern, logExtention));
        logger.setLevel(Level.INFO);
    }

    private void createLogFolder(File logFolder) throws IOException {
        if (!logFolder.mkdirs()) {
            throw new IOException("could note create log directory");
        }
    }
}
