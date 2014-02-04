package de.jodamob.android.tracing;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import android.content.Context;
import android.net.Uri;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.UrlConnectionDownloader;

public class TracedPicassoDownloader implements Downloader {

    private final Downloader downloader;
    private final Tracer tracer;

    public TracedPicassoDownloader(Tracer tracer, Context context) {
        this(tracer, createDefaultDownloader(context));
    }
    
    public TracedPicassoDownloader(Tracer tracer, Downloader downloader) {
        this.tracer = tracer;
        this.downloader = downloader;
    }
    
    @Override
    public Response load(Uri uri, boolean localCacheOnly) throws IOException {
        long startTime = getTime();
        try {
            return executeTraced(uri, localCacheOnly, startTime);
        } catch (IOException e) {
            traceException(uri, e, startTime);
            throw e;
        }
    }
    
    private void traceException(Uri uri, IOException e, long startTime) {
        if (isResponseException(e)) {
            try {
                traceStatus(uri, e, startTime);
                return;
            } catch(Exception parseError) {
                parseError.printStackTrace();
            }
        }
        trace(uri, e, startTime, getTime());
    }

    private void traceStatus(Uri uri, IOException e, long startTime) throws IOException {
        int status = Integer.parseInt(e.getMessage().substring(0, 3));
        trace(uri, status, startTime);
        return;
    }

    private boolean isResponseException(IOException e) {
        return e.getClass().getName().endsWith("ResponseException");  // taken from picasso code
    }

    private void trace(Uri uri, IOException e, long startTime, long endTime) {
        tracer.trace(uri.toString(), startTime, endTime, e);
    }
    
    private void trace(Uri uri, int statusCode, long startTime) throws IOException {
        tracer.trace(uri.toString(), statusCode, startTime, getTime(), 0, 0);
    }

    private Response executeTraced(Uri uri, boolean localCacheOnly, long startTime) throws IOException {
        Response response = downloader.load(uri, localCacheOnly);
        // might be wrong, could also be 2XX but for image get would be weird
        trace(uri, response == null ? HTTP_BAD_REQUEST : HTTP_OK, startTime);  
        return response;
    }

    private static Downloader createDefaultDownloader(Context context) {
        try {
          Class.forName("com.squareup.okhttp.OkHttpClient");
          return OkHttpLoaderCreator.create(context);
        } catch (ClassNotFoundException e) {
          return new UrlConnectionDownloader(context);
        }
      }
    
    private static class OkHttpLoaderCreator {
        static Downloader create(Context context) {
          return new OkHttpDownloader(context);
        }
    }
    
    private long getTime() {
        return System.currentTimeMillis();
    }
}
