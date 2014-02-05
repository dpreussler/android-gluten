package de.jodamob.android.logging;

public class CrashlyticsLogger implements _Log {

    private final _Log redirectLog;
    private final CrashlyticsReporter exceptionReporter = new CrashlyticsReporter();

    public CrashlyticsLogger(_Log redirectLog) {
        this.redirectLog = redirectLog; 
    }

    @Override
    public  int v(String tag, String message) {
        return redirectLog.v(tag, message);
    }

    @Override
    public  int v(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(tr);
        return redirectLog.v(tag, message, tr);
    }

    @Override
    public  int d(String tag, String message) {
        return redirectLog.d(tag, message);
    }

    @Override
    public  int d(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(tr);
        return redirectLog.d(tag, message, tr);
    }

    @Override
    public  int i(String tag, String message) {
        return redirectLog.i(tag, message);
    }

    @Override
    public  int i(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(tr);
        return redirectLog.i(tag, message, tr);
    }

    @Override
    public  int w(String tag, String message) {
        return redirectLog.w(tag, message);
    }

    @Override
    public  int w(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(tr);
        return redirectLog.w(tag, message, tr);
    }

    @Override
    public  int w(String tag, Throwable tr) {
        return redirectLog.w(tag, tr);
    }

    @Override
    public  int e(String tag, String message) {
        return redirectLog.e(tag, message);
    }

    @Override
    public  int e(String tag, String message, Throwable tr) {
        exceptionReporter.reportLoggedException(tr);
        return redirectLog.e(tag, message);
    }

    @Override
    public  int e(String message) {
        return redirectLog.e(message);
    }

    @Override
    public  int e(String msg, Exception tr) {
        exceptionReporter.reportLoggedException(tr);
        return redirectLog.e(msg, tr);
    }

    @Override
    public  int wtf(String tag, String message, Throwable tr) {
        exceptionReporter.reportWtfException(tr);
        return redirectLog.wtf(tag, message, tr);
    }

    @Override
    public  int wtf(String tag, Exception tr) {
        exceptionReporter.reportWtfException(tr);
        return redirectLog.wtf(tag, tr);
    }

    @Override
    public  int wtf(String tag, String message) {
        return redirectLog.wtf(tag, message);
    }

    @Override
    public  int wtf(Throwable tr) {
        exceptionReporter.reportWtfException(tr);
        return redirectLog.wtf(tr);
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return redirectLog.getStackTraceString(tr);
    }
}
