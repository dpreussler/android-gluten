package de.jodamob.android.espresso;

import com.google.android.apps.common.testing.ui.espresso.IdlingResource;
import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.JobManager;

public class PriorityJobQueueIdleMonitor implements IdlingResource {
    
    private static final int LOW_PRIORITY = 0;
    private final JobManager queue;
    
    public PriorityJobQueueIdleMonitor(JobManager queue) {
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
        if (!isIdleNow()) {
            addIdleWatcherJob(callback);
        }
    }

    private long addIdleWatcherJob(final ResourceCallback callback) {
        return queue.addJob(LOW_PRIORITY, new IdleCheckJob(true, callback));
    }

    private final class IdleCheckJob extends BaseJob {
        
        private final ResourceCallback callback;
        private static final long serialVersionUID = -7531425496860713384L;
    
        private IdleCheckJob(boolean requiresNetwork, ResourceCallback callback) {
            super(requiresNetwork);
            this.callback = callback;
        }
    
        @Override
        public void onAdded() {
        }
    
        @Override
        protected void onCancel() {
            notifyOrReAdd(callback);
        }
    
        @Override
        public void onRun() throws Throwable {
            notifyOrReAdd(callback);
        }
    
        private void notifyOrReAdd(final ResourceCallback callback) {
            if (!isIdleNow()) {
                addIdleWatcherJob(callback);  // re-add us
            } else {
                callback.onTransitionToIdle();
            }
        }
    
        @Override
        protected boolean shouldReRunOnThrowable(Throwable arg0) {
            return false;
        }
    }

}
