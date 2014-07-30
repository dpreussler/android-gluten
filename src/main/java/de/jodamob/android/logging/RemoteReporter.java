package de.jodamob.android.logging;

/**
 * implement this for your server side tracing tool of choice
 */
public interface RemoteReporter {
    
    /**
     * will be called for every throwable that is logged
     */
    void reportLoggedException(String message, Throwable tr);

    /**
     * will be called from Log.wtf
     */
    void reportWtfException(Throwable e);

    /**
     * will be called from Log.wtf
     */
    void reportWtfException(String message, Throwable e);
}
