package de.jodamob.android.phrase;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;

import com.squareup.phrase.Phrase;

import java.util.Locale;

public class SafePhrase {

    private Phrase phrase;

    public SafePhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public static SafePhrase from(CharSequence sequence) {
        return new SafePhrase(Phrase.from(sequence));
    }

    public static SafePhrase from(Resources resources, int resourceId) {
        return new SafePhrase(Phrase.from(resources, resourceId));
    }

    public static SafePhrase from(Activity activity, int resourceId) {
        return new SafePhrase(Phrase.from(activity, resourceId));
    }

    public SafePhrase put(String key, String value) {
        try {
            phrase = phrase.put(key, value);
        } catch(IllegalArgumentException e) {
            Log.wtf("missing " + key + " in " + Locale.getDefault().getDisplayLanguage(), e);
        }
        return this;
    }

    public CharSequence format() {
        return phrase.format();
    }
}
