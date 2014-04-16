package de.jodamob.android.espresso;

import android.content.Context;

import com.google.android.apps.common.testing.ui.espresso.IdlingResource;
import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;

import de.jodamob.android.logging.Log;

public class ProfiledJobManager extends JobManager {

    private static final int LOW_PRIORITY = 0;
    private QueueNotifier notifier = QueueNotifier.NO_NOTIFIER;

    public ProfiledJobManager(Context context) {
        super(context);
    }

    public ProfiledJobManager(Context context, String id) {
        super(context, id);
    }

    public ProfiledJobManager(Context context, Configuration config) {
        super(context, config);
    }

    public void registerNotifier(QueueNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public long addJob(int priority, long delay, BaseJob baseJob) {
        long job = super.addJob(priority, delay, baseJob);

        super.addJob(LOW_PRIORITY, delay, new BaseJob(true) {

            @Override
            public void onAdded() {
                Log.i("espresso", "watcher added");
            }

            @Override
            protected void onCancel() {
                notifyOrReAdd();
            }

            @Override
            public void onRun() throws Throwable {
                notifyOrReAdd();
            }

            private void notifyOrReAdd() {
                Log.i("espresso", "watcher state " + count());
                if (count() == 0) {
                    notifier.onQueueEmpty();
                }
            }

            @Override
            protected boolean shouldReRunOnThrowable(Throwable throwable) {
                return false;
            }
        });

        return job;
    }
}
