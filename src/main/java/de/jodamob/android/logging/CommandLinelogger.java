package de.jodamob.android.logging;

public class CommandLinelogger implements Logging {
    @Override
    public int v(String tag, String message) {
        return 0;
    }

    @Override
    public int v(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int d(String tag, String message) {
        return 0;
    }

    @Override
    public int d(String tag, String message, Throwable tr) {
        return 0;
    }

    @Override
    public int i(String tag, String message) {
        System.out.println(message);
        return 0;
    }

    @Override
    public int i(String tag, String message, Throwable tr) {
        System.out.println(message + tr.toString());
        return 0;
    }

    @Override
    public int w(String tag, String message) {
        System.err.println(message);
        return 0;
    }

    @Override
    public int w(String tag, String message, Throwable tr) {
        System.err.println(message + tr.toString());
        return 0;
    }

    @Override
    public int w(String tag, Throwable tr) {
        System.err.println(tag + tr.toString());
        return 0;
    }

    @Override
    public int e(String tag, String message) {
        return 0;
    }

    @Override
    public int e(String tag, String message, Throwable tr) {
        System.err.println(message + tr.toString());
        return 0;
    }

    @Override
    public int e(String message) {
        System.err.println(message);
        return 0;
    }

    @Override
    public int e(String msg, Throwable tr) {
        System.err.println(msg + tr.toString());
        return 0;
    }

    @Override
    public int wtf(String tag, String message, Throwable tr) {
        return e(tag, message, tr);
    }

    @Override
    public int wtf(String tag, Throwable tr) {
        return e(tag, tr);
    }

    @Override
    public int wtf(String tag, String message) {
        return e(tag, message);
    }

    @Override
    public int wtf(Throwable tr) {
        return e(tr);
    }

    @Override
    public int logStackTrace(String s) {
        new Throwable().fillInStackTrace().printStackTrace();
        return 0;
    }

    @Override
    public String getStackTraceString(Throwable tr) {
        return null;
    }

    @Override
    public int e(Throwable throwable) {
        throwable.printStackTrace();
        System.err.println(throwable.toString());
        return 0;
    }
}
