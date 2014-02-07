package de.jodamob.android.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class StreamUtils {
    public static final void copy(final InputStream from, final OutputStream to) throws IOException {
        final byte[] buffer = new byte[8192];
        while (true) {
            final int newBytes = from.read(buffer);
            if (newBytes == -1) {
                break;
            }
            to.write(buffer, 0, newBytes);
        }
        to.flush();
    }
    
    private StreamUtils() {
    }
}
