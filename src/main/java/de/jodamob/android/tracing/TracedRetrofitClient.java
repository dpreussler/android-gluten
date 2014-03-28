package de.jodamob.android.tracing;

import java.io.IOException;
import java.util.List;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

// TODO this should be done via the Profiler API from Retrofit
@Deprecated
public class TracedRetrofitClient implements Client{
    
    private final Client client;
    private final Tracer tracer;

    public TracedRetrofitClient(Client client, Tracer tracer) {
        this.client = client;
        this.tracer = tracer;
    }
    
    @Override public Response execute(Request request) throws IOException {
        long startTime = getTime();
        try {
            return executeTraced(request, startTime);
        } catch (IOException e) {
            trace(request, e, startTime, getTime());
            throw e;
        }
    }

    private Response executeTraced(Request request, long startTime) throws IOException {
        Response response = client.execute(request);
        trace(request, response, startTime);
        return response;
    }

    private void trace(Request request, IOException e, long startTime, long endTime) {
        tracer.trace(request.getUrl(), startTime, endTime, e);
    }

    private void trace(Request request, Response response, long startTime) throws IOException {
        long bytesReceived = getBytesReceived(response);
        tracer.trace(request.getUrl(), getStatus(response), startTime, getTime(), 0, bytesReceived);
    }

    private int getStatus(Response response) {
        return response != null ? response.getStatus() : -1;
    }

    private long getBytesReceived(Response response) {
        return response != null ? getResponseSize(response.getHeaders()) : 0;
    }

    private long getResponseSize(List<Header> headers) {
        for (Header header : headers) {
            if (isContentLenght((header.getName()))) {
                return Long.parseLong(header.getValue());
            }
        }
        return 0L;
    }

    private boolean isContentLenght(String name) {
        return "Content-Length".equalsIgnoreCase(name);
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
