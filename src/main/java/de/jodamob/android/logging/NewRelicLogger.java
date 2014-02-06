package de.jodamob.android.logging;

import java.util.Date;
import com.newrelic.agent.android.NewRelic;


public class NewRelicLogger extends RemoteToolLogger {
    
    public NewRelicLogger() {
        this(new SilentLogger());
    }
    
    public NewRelicLogger(_Log redirectLogger) {
        super(new NewRelicRepoter(), redirectLogger);
    }

    private static final class NewRelicRepoter implements RemoteReporter {
        
        @Override
        public void reportLoggedException(String message, Throwable tr) {
            long time = new Date().getTime();
            NewRelic.noticeNetworkFailure(message, time, time, new Exception(tr));
        }
    
        @Override
        public void reportWtfException(Throwable tr) {
            long time = new Date().getTime();
            NewRelic.noticeNetworkFailure("WTF", time, time, new Exception(tr));
        }
    }
}
