package de.jodamob.android.tracing;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import de.jodamob.android.logging.Log;
import de.jodamob.android.logging.SilentLogger;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedString;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.longThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TracedRetrofitClientTest {

    TracedRetrofitClient tested;
    Client client;
    Tracer tracer;
    
    @Before
    public void setup() throws IOException {
        Log.initLogger(new SilentLogger());
        client = mock(Client.class);
        tracer = mock(Tracer.class);
        tested = new TracedRetrofitClient(client, tracer);
    }
    
    @Test
    public void should_report_status_code_and_url() throws IOException {
        when(client.execute(any(Request.class))).thenReturn(new Response(200, "OK", Collections.<Header> emptyList(), null));
        tested.execute(new Request("", "http://jodamob.de", Collections.<Header> emptyList(), null));
        verify(tracer).trace(eq("http://jodamob.de"), eq(200), anyLong(), anyLong(), anyLong(), anyLong());
    }
    
    @Test
    public void should_report_time_spent() throws IOException {
        when(client.execute(any(Request.class))).thenReturn(new Response(200, "OK", Collections.<Header> emptyList(), null));
        long before = new Date().getTime();
        tested.execute(new Request("", "", Collections.<Header> emptyList(), null));
        verify(tracer).trace(anyString(), anyInt(), longThat(greaterThanOrEqualTo(before)),  anyLong(), anyLong(), anyLong());
    }
    
    @Test
    public void should_report_status_errors() throws IOException {
        when(client.execute(any(Request.class))).thenThrow(new IOException(""));
        try {
            tested.execute(new Request("", "http://jodamob.de", Collections.<Header> emptyList(), null));
            fail("expected exception");
        } catch(IOException e) {}
        verify(tracer).trace(eq("http://jodamob.de"), anyLong(), anyLong(), any(IOException.class));
    }

    @Test
    public void should_report_body() throws IOException {
        when(client.execute(any(Request.class))).thenReturn(new Response(400, "ERROR", Collections.<Header> emptyList(), new TypedString("THE_BODY")));
        tested.execute(new Request("", "", Collections.<Header> emptyList(), null));
        verify(tracer).trace(anyString(), eq(400), anyLong(),  anyLong(), anyLong(), anyLong(), eq("THE_BODY"));
    }

    @Test
    public void should_not_report_body_on_non_error() throws IOException {
        when(client.execute(any(Request.class))).thenReturn(new Response(200, "ERROR", Collections.<Header> emptyList(), new TypedString("THE_BODY")));
        tested.execute(new Request("", "", Collections.<Header> emptyList(), null));
        verify(tracer).trace(anyString(), eq(200), anyLong(),  anyLong(), anyLong(), anyLong());
    }

    @Test
    public void should_not_report_body_on_too_long() throws IOException {
        TypedInput body = mock(TypedInput.class);
        when(body.length()).thenReturn(new Long(Integer.MAX_VALUE));
        when(client.execute(any(Request.class))).thenReturn(new Response(200, "ERROR", Collections.<Header> emptyList(), body));
        tested.execute(new Request("", "", Collections.<Header> emptyList(), null));
        verify(tracer).trace(anyString(), eq(200), anyLong(),  anyLong(), anyLong(), anyLong());
    }

    @Test
    public void should_handle_exception_when_collecting_body() throws IOException {
        TypedInput mock = mock(TypedInput.class);
        when(mock.length()).thenThrow(new RuntimeException());
        when(client.execute(any(Request.class))).thenReturn(new Response(400, "ERROR", Collections.<Header> emptyList(), mock));
        tested.execute(new Request("", "", Collections.<Header> emptyList(), null));
        verify(tracer).trace(anyString(), eq(400), anyLong(),  anyLong(), anyLong(), anyLong(), eq(""));
    }
}
