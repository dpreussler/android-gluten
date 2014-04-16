package de.jodamob.android.espresso;

import com.google.android.apps.common.testing.ui.espresso.IdlingResource;
import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.JobManager;

public class PriorityJobQueueIdleMonitor implements IdlingResource {
    
    private final ProfiledJobManager queue;

    public PriorityJobQueueIdleMonitor(ProfiledJobManager queue) {
        this.queue = queue;
    }

    @Override
    public String getName() {
        return PriorityJobQueueIdleMonitor.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        return queue.count() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(final ResourceCallback callback) {
        queue.registerNotifier(new QueueNotifier() {
            @Override
            public void onQueueEmpty() {
                callback.onTransitionToIdle();
            }
        });
    }
}
