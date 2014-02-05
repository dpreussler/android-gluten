package de.jodamob.android.logging;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import android.content.Context;

@RunWith(RobolectricTestRunner.class)
public class FileLoggerSetupTest {

    Logger logger;
    FileLoggerSetup tested;
    Context context;
    
    @Before
    public void setup() {
        context = mock(Context.class);
        tested = new FileLoggerSetup(context);
        Logger javaLogger = Logger.getLogger("testing");
        javaLogger.addHandler(mock(Handler.class));
        logger = spy(javaLogger);
    }
    
    @Test
    public void should_remove_previous_handlers() {
        tested.prepare(logger);
        verify(logger).getHandlers();
        verify(logger).removeHandler(any(Handler.class));
    }
    
    @Test
    public void should_register_exception_handler() {
        LoggingUncaughtExceptionHandler exceptions = mock(LoggingUncaughtExceptionHandler.class);
        tested.prepare(logger, exceptions);
        verify(exceptions).register();
    }
    
    @Test
    public void should_create_log_folder() throws IOException {
        File folder = spy(new File(""));
        when(folder.mkdirs()).thenReturn(true);
        when(context.getExternalCacheDir()).thenReturn(folder);
        tested.prepare(logger);
        verify(folder).mkdirs();
        
    }
}
