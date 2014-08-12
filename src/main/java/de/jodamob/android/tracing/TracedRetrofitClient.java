package de.jodamob.android.tracing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import de.jodamob.android.logging.Log;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * use this when you want to trace with body
 */
public class TracedRetrofitClient implements Client{

    private static final int BUFFER_SIZE = 0x1000;
    private static final long MAX_BODY_TO_LOG_SIZE = 20000L;

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

        int status = getStatus(response);
        if (status >= 400) {
            tracer.trace(request.getUrl(), status, startTime, getTime(), 0, bytesReceived, getBodyString(response));
        } else {
            tracer.trace(request.getUrl(), status, startTime, getTime(), 0, bytesReceived);
        }
    }

    private int getStatus(Response response) {
        return response != null ? response.getStatus() : -1;
    }

    private long getBytesReceived(Response response) {
        return response != null ? getResponseSize(response.getHeaders()) : 0;
    }

    private long getResponseSize(List<Header> headers) {
        for (Header header : headers) {
            if (isContentLength((header.getName()))) {
                return Long.parseLong(header.getValue());
            }
        }
        return 0L;
    }

    private boolean isContentLength(String name) {
        return "Content-Length".equalsIgnoreCase(name);
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    private static String getBodyString(Response response) throws IOException {
        try {
            TypedInput body = response.getBody();
            if (body != null && body.length() < MAX_BODY_TO_LOG_SIZE) {
                return convertToString(response, body);
            }
        } catch(Exception e) {
            Log.wtf(e);
        }
        return "";
    }

    private static String convertToString(Response response, TypedInput body) throws IOException {
        if (!(body instanceof TypedByteArray)) {
            return convertToString(readBodyToBytesIfNecessary(response).getBody());
        }
        return convertToString(body);
    }

    private static String convertToString(TypedInput body) throws UnsupportedEncodingException {
        byte[] bodyBytes = ((TypedByteArray) body).getBytes();
        return new String(bodyBytes, MimeUtil.parseCharset(body.mimeType()));
    }

    private static Response readBodyToBytesIfNecessary (Response response) throws IOException {
        TypedInput body = response.getBody();
        if (body == null || body instanceof TypedByteArray) {
            return response;
        }
        return replaceResponseBody(response, new TypedByteArray(body.mimeType(), stream(body.in())));
    }

    private static Response replaceResponseBody(Response response, TypedInput body) {
        return new Response(response.getStatus(), response.getReason(), response.getHeaders(), body);
    }

    private static byte[] stream(InputStream stream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (stream != null) {
            stream(stream, outputStream);
        }
        return outputStream.toByteArray();
    }

    private static void stream(InputStream stream, ByteArrayOutputStream outputStream) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        int r;
        while ((r = stream.read(buf)) != -1) {
            outputStream.write(buf, 0, r);
        }
    }
}
