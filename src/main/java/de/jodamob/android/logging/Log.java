package de.jodamob.android.logging;


/**
 * mimics the "interface" of android log but can use a java logger
 * and adds some more methods to log without tag
 * will forward to android log for verbose and debug
 * and forward and log to file for higher ones
 */
public final class Log {

    private static _Log logger = new LogCatLogger();
    
    public static int v(String tag, String message) {
        return android.util.Log.v(tag, message);
    }

    public static int v(String tag, String message, Throwable tr) {
        return logger.v(tag, message, tr);
    }

    public static int d(String tag, String message) {
        return logger.d(tag, message);
    }

    public static int d(String tag, String message, Throwable tr) {
        return logger.d(tag, message, tr);
    }

    public static int i(String tag, String message) {
        return logger.i(tag, message);
    }

    public static int i(String tag, String message, Throwable tr) {
        return logger.i(tag, message, tr);
    }

    public static int w(String tag, String message) {
        return logger.w(tag, message);
    }

    public static int w(String tag, String message, Throwable tr) {
        return logger.w(tag, message, tr);
    }

    public static int w(String tag, Throwable tr) {
        return logger.w(tag, tr);
    }

    public static int e(String tag, String message) {
        return logger.e(tag, message);
    }

    public static int e(String tag, String message, Throwable tr) {
        return logger.e(tag, message);
    }

    public static int e(String message) {
        return logger.e(message);
    }

    public static int e(String msg, Exception tr) {
        return logger.e(msg, tr);
    }

    public static int wtf(String tag, String message, Throwable tr) {
        return logger.wtf(tag, message, tr);
    }

    public static int wtf(String tag, Exception tr) {
        return logger.wtf(tag, tr);
    }

    public static int wtf(String tag, String message) {
        return logger.wtf(tag, message);
    }

    public static int wtf(Throwable tr) {
        return logger.wtf(tr);
    }

    public static String getStackTraceString(Throwable tr) {
        return logger.getStackTraceString(tr);
    }
    
    public static void initLogger(_Log log) {
        logger = log != null ? log : new LogCatLogger(); 
    }
}
