package de.jodamob.android.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

class SimpleFileHandler extends FileHandler {
    
    private static final int MAX_LOGFILE_SIZE_IN_BYTES = 1024 * 1024;
    private static final int NUMBER_OF_LOGFILES = 3;

    SimpleFileHandler(File logFolder, String logpattern, String logExtention) throws IOException {
        super(logFolder.getAbsolutePath() + "/" + logpattern + "%g" + logExtention, MAX_LOGFILE_SIZE_IN_BYTES, NUMBER_OF_LOGFILES);
        setFormatter(new SimpleLogFormatter());
    }
}
