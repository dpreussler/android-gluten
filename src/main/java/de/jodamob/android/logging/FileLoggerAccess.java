package de.jodamob.android.logging;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import android.content.Context;
import de.jodamob.android.utils.Closeables;
import de.jodamob.android.utils.StreamUtils;

class FileLoggerAccess {
    
    private static final String FOLDER_NAME = "logs";
    private static final String FILE_PATTERN = "applog";
    private static final String FILE_EXTENSION = ".log";
    private static final String ACCUMMULATED_LOG = "logscollected.txt";
    private final Context context;

    FileLoggerAccess(Context context) {
        this.context = context;
    }
    
    final void prepare(Logger logger) {
        prepare(logger, getLogDir(), FILE_PATTERN, FILE_EXTENSION);
    }
    
    final File getAsSingleLogfile() {
        File logDir = getLogDir();
        File newFile = new File(logDir, ACCUMMULATED_LOG);
        OutputStream out = null;
        try {
            out = new FileOutputStream(newFile);
            writeFilesToStream(logDir, out);
        } catch (IOException e) {
            e.printStackTrace(); 
        } finally {
            Closeables.closeQuietly(out);
        }
    
        return newFile;
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

    private void createLogFolder(File logFolder) {
        logFolder.mkdirs();
    }

    private void writeFilesToStream(File dir, OutputStream out) throws IOException {
        File[] files = dir.listFiles(new LogFileFilter());
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.length() == 0) {
                    continue;
                }
                copyInto(out, file);
            }
        }
    }

    private void copyInto(OutputStream out, File file) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(file);
        try {
            StreamUtils.copy(in, out);
        } finally {
            Closeables.closeQuietly(in);
        }
    }

    private static final class LogFileFilter implements FileFilter {
        @Override
        public boolean accept(File path) {
            if (path.isDirectory()) {
                return false;
            }
            String name = path.getName();
            return name.startsWith(FILE_PATTERN) && name.endsWith(FILE_EXTENSION);
        }
    }
}
