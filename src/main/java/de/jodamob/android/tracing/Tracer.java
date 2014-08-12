package de.jodamob.android.tracing;

import java.io.IOException;

public interface Tracer {
    
    void trace(String url, long startTime, long endTime, IOException e);
    void trace(String url, int statusCode, long startTime, long endTime, long bytesSent, long bytesReceived);
    void trace(String url, int statusCode, long startTime, long endTime,  long bytesSent, long bytesReceived, String response);
}
