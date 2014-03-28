package de.jodamob.android.tracing;

import java.io.IOException;
import java.util.List;

import retrofit.Profiler;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

public class TracedRetrofitProfiler implements Profiler<Long>{

    private final Tracer tracer;

    public TracedRetrofitProfiler(Tracer tracer) {
        this.tracer = tracer;
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    @Override
    public Long beforeCall() {
        return getTime();
    }

    @Override
    public void afterCall(RequestInformation requestInfo, long elapsedTime, int statusCode, Long beforeCallData) {
        tracer.trace(getUrl(requestInfo), statusCode, beforeCallData, beforeCallData+elapsedTime, requestInfo.getContentLength(), 0);
    }

    private String getUrl(RequestInformation requestInfo) {
        String baseUrl = requestInfo.getBaseUrl();
        String relativePath = requestInfo.getRelativePath();
        if ((baseUrl.endsWith("/")) || (relativePath.startsWith("/"))) {
            return baseUrl + relativePath;
        }
        return baseUrl + "/" + relativePath;
    }
}
