package de.jodamob.android.logging;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import android.content.Context;

@RunWith(RobolectricTestRunner.class)
public class FileLoggerTest {

    FileLogger tested;
    _Log logger;
    
    @Before 
    public void setup() {
        logger = mock(_Log.class);
        tested = new FileLogger(mock(Context.class), logger);
    }
    
    @Test
    public void should_pass_through() {
        tested.e("error");
        verify(logger).e("error");
    }

}
