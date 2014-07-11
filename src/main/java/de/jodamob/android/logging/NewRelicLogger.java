package de.jodamob.android.logging;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import com.newrelic.agent.android.NewRelic;


public class NewRelicLogger extends RemoteToolLogger {
    
    public NewRelicLogger() {
        this(new SilentLogger());
    }
    
    public NewRelicLogger(Logging redirectLogger) {
        super(new NewRelicRepoter(), redirectLogger);
    }

    private static final class NewRelicRepoter implements RemoteReporter {
        
        @Override
        public void reportLoggedException(String message, Throwable tr) {
            long time = new Date().getTime();
            try {
                if (message.isEmpty()) {
                    message = "none";
                }
                NewRelic.noticeNetworkFailure("http://" + URLEncoder.encode(message, "utf-8"), time, time, new Exception(tr));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    
        @Override
        public void reportWtfException(Throwable tr) {
            long time = new Date().getTime();
            NewRelic.noticeNetworkFailure("http://WTF", time, time, new Exception(tr));
        }
    }
}
