package de.jodamob.android.logging;

/**
 * implement this for your server side tracing tool of choice
 */
class CrashlyticsReporter {
    
    /**
     * will be called for every throwable that is logged
     */
    void reportLoggedException(Throwable tr) {
        // TODO        
    }
    
    /**
     * will be called from Log.wtf
     */
    void reportWtfException(Throwable e) {
        // TODO
    }
}
