package de.jodamob.android.phrase;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;

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

    public static SafePhrase from(Context context, int resourceId) {
        return new SafePhrase(Phrase.from(context, resourceId));
    }

    public static SafePhrase from(View view, int resourceId) {
        return new SafePhrase(Phrase.from(view, resourceId));
    }

    public static SafePhrase from(Resources resources, int resourceId) {
        return new SafePhrase(Phrase.from(resources, resourceId));
    }

    public static SafePhrase from(Fragment fragment, int resourceId) {
        return new SafePhrase(Phrase.from(fragment, resourceId));
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
        try {
            return phrase.format();
        } catch(IllegalArgumentException e) {
            Log.wtf(e.getMessage(), e);
        }
        return phrase.toString();
    }
}
