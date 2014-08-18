package de.jodamob.android.logging;


public class RemoteToolLogger extends RedirectedLogging {

    private final RemoteReporter exceptionReporter;

    public RemoteToolLogger(RemoteReporter reporter) {
        this(reporter, new SilentLogger());
    }
    
    public RemoteToolLogger(RemoteReporter reporter, Logging redirectLog) {
        super(redirectLog);
        exceptionReporter = reporter;
    }

    @Override
    public  int v(String tag, String message) {
        return super.v(tag, message);
    }

    @Override
    public  int v(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(message, tr);
        return super.v(tag, message, tr);
    }

    @Override
    public  int d(String tag, String message) {
        return super.d(tag, message);
    }

    @Override
    public  int d(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(message, tr);
        return super.d(tag, message, tr);
    }

    @Override
    public  int i(String tag, String message) {
        return super.i(tag, message);
    }

    @Override
    public  int i(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(message, tr);
        return super.i(tag, message, tr);
    }

    @Override
    public  int w(String tag, String message) {
        return super.w(tag, message);
    }

    @Override
    public  int w(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(message, tr);
        return super.w(tag, message, tr);
    }

    @Override
    public  int w(String tag, Throwable tr) {
        return super.w(tag, tr);
    }

    @Override
    public  int e(String tag, String message) {
        return super.e(tag, message);
    }

    @Override
    public  int e(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(message, tr);
        return super.e(tag, message, tr);
    }

    @Override
    public  int e(String message) {
        return super.e(message);
    }

    @Override
    public  int e(String msg, Throwable tr) {
        exceptionReporter.reportLoggedException(msg, tr);
        return super.e(msg, tr);
    }

    @Override
    public int e(Throwable tr) {
        return super.e(tr);
    }

    @Override
    public  int wtf(String tag, String message, Throwable tr) {
        exceptionReporter.reportWtfException(tag + message, tr);
        return super.wtf(tag, message, tr);
    }

    @Override
    public  int wtf(String tag, Throwable tr) {
        exceptionReporter.reportWtfException(tag, tr);
        return super.wtf(tag, tr);
    }

    @Override
    public  int wtf(String tag, String message) {
        return super.wtf(tag, message);
    }

    @Override
    public  int wtf(Throwable tr) {
        exceptionReporter.reportWtfException(tr);
        return super.wtf(tr);
    }

    @Override
    public int logStackTrace(String message) {
        exceptionReporter.reportLoggedException(message, new Throwable().fillInStackTrace());
        return super.logStackTrace(message);
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return super.getStackTraceString(tr);
    }
}
