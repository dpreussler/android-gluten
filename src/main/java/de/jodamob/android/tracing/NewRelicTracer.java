package de.jodamob.android.tracing;

import java.io.IOException;
import com.newrelic.agent.android.NewRelic;

public class NewRelicTracer implements Tracer {

    @Override
    public void trace(String url, long startTime, long endTime, IOException e) {
        NewRelic.noticeNetworkFailure(url, startTime, endTime, e);
    }

    @Override
    public void trace(String url, int statusCode, long startTime, long endTime, long bytesSent, long bytesReceived) {
        NewRelic.noticeHttpTransaction(url, statusCode, startTime, endTime, bytesSent, bytesReceived);
    }

    @Override
    public void trace(String url, int statusCode, long startTime, long endTime, long bytesSent, long bytesReceived, String response) {
        NewRelic.noticeHttpTransaction(url, statusCode, startTime, endTime, bytesSent, bytesReceived, response);
    }

}
