package de.jodamob.android.logging;

import com.crashlytics.android.Crashlytics;


public class CrashlyticsLogger extends RemoteToolLogger {
    
    public CrashlyticsLogger() {
        this(new SilentLogger());
    }
    
    public CrashlyticsLogger(_Log redirectLogger) {
        super(new CrashlyticsRepoter(), redirectLogger);
    }

    private static final class CrashlyticsRepoter implements RemoteReporter {
        
        @Override
        public void reportLoggedException(String message, Throwable tr) {
            Crashlytics.log(message  + tr.toString());
        }
    
        @Override
        public void reportWtfException(Throwable tr) {
            Crashlytics.logException(tr);
        }
    }
}
