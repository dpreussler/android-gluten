package de.jodamob.android.utils;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CloseablesTest {

    @Test
    public void testCloseQuietly() throws IOException {

        should_handle_null();
        should_swallow_IOException();
    }

    @Test
    public void should_handle_null() {
        Closeables.closeQuietly(null);
    }

    @Test
    public void should_swallow_IOException() throws IOException {
        InputStream in = mock(InputStream.class);
        doThrow(new IOException()).when(in).close();
        Closeables.closeQuietly(in);
    }
    
    @Test
    public void should_swallow_RuntimeException() throws IOException {
        OutputStream out = mock(OutputStream.class);
        doThrow(new RuntimeException()).when(out).close();
        Closeables.closeQuietly(out);
    }

}
