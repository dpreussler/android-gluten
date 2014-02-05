package de.jodamob.android.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;


class SimpleLogFormatter extends Formatter {

    private static final String DATE_PATTERN = "MM dd - HH:mm:ss";
    private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());

    @Override
    public String format(final LogRecord rec) {
        final StringBuilder sb = new StringBuilder(1000);
        sb.append(rec.getLevel());
        sb.append(' ');
        sb.append(formatter.format(new Date(rec.getMillis())));
        sb.append(' ');
        sb.append(formatMessage(rec));
        sb.append('\n');
        return sb.toString();
    }
}
