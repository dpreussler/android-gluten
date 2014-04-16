package de.jodamob.android.espresso;

interface QueueNotifier {
    QueueNotifier NO_NOTIFIER = new QueueNotifier() {
        @Override
        public void onQueueEmpty() {
        }
    };
    void onQueueEmpty();
}
