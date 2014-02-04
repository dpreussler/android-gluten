package de.jodamob.android.espresso;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import com.google.android.apps.common.testing.ui.espresso.IdlingResource.ResourceCallback;
import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.JobManager;

public class PriorityJobQueueIdleMonitorTest {
    
    JobManager queue;
    PriorityJobQueueIdleMonitor tested;

    @Before
    public void setup() {
        queue = mock(JobManager.class);
        tested = new PriorityJobQueueIdleMonitor(queue);
    }
    
    @Test
    public void should_be_idle_initially() {
        assertTrue(tested.isIdleNow());
    }
    
    @Test
    public void should_not_be_idle_on_jobs() {
        when(queue.count()).thenReturn(1);
        assertFalse(tested.isIdleNow());
    }
    
    @Test
    public void register_should_add_job_if_not_idle() {
        when(queue.count()).thenReturn(1);
        ResourceCallback callback = mock(ResourceCallback.class);
        tested.registerIdleTransitionCallback(callback);
        verify(queue).addJob(anyInt(), any(BaseJob.class));
    }
    
}
