package de.jodamob.android.utils;

import android.view.View;

public class ViewUtils {

    public static void hideView(View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void showView(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void showOrRemove(boolean show, View... views) {
        for (View view : views) {
            view.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public static void showOrHide(boolean show, View view) {
        view.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public static void removeView(View view) {
        view.setVisibility(View.GONE);
    }
}
