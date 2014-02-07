package de.jodamob.android.utils;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

public class StreamUtilsTest {

    @Test
    public void should_copy_data() throws IOException {
        InputStream from = new ByteArrayInputStream("this is a test".getBytes());
        ByteArrayOutputStream to = new ByteArrayOutputStream();
        StreamUtils.copy(from, to);
        assertEquals("this is a test", new String(to.toByteArray()));
    }
}
