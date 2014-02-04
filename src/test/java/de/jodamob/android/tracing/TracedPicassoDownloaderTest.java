package de.jodamob.android.tracing;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import android.net.Uri;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Downloader.Response;

@RunWith(RobolectricTestRunner.class)
public class TracedPicassoDownloaderTest {

    Tracer tracer;
    Downloader downloader;
    TracedPicassoDownloader tested;
    
    @Before
    public void setup() {
        tracer = mock(Tracer.class);
        downloader = mock(Downloader.class);
        tested = new TracedPicassoDownloader(tracer, downloader);
    }
    
    @Test
    public void should_report_status_code_and_url() throws IOException {
        when(downloader.load(any(Uri.class), anyBoolean())).thenReturn(mock(Response.class));
        tested.load(Uri.parse("http://jodamob.de"), false);
        verify(tracer).trace(eq("http://jodamob.de"), eq(200), anyLong(), anyLong(), anyLong(), anyLong());
    }
    
    @Test
    public void should_report_time_spent() throws IOException {
        when(downloader.load(any(Uri.class), anyBoolean())).thenReturn(mock(Response.class));
        long before = new Date().getTime();
        tested.load(mock(Uri.class), false);
        verify(tracer).trace(anyString(), intThat(greaterThan((int)before)), anyLong(), anyLong(), anyLong(), anyLong());
    }
    
    @Test
    public void should_report_status_errors() throws IOException {
        when(downloader.load(any(Uri.class), anyBoolean())).thenThrow(new IOException(""));
        try {
            tested.load(Uri.parse("http://jodamob.de"), false);
            fail("expected exception");
        } catch(IOException e) {}
        verify(tracer).trace(eq("http://jodamob.de"), anyLong(), anyLong(), any(IOException.class));
    }
}
