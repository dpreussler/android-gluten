package de.jodamob.android.autolayout;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AutoLayoutDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(NameConverter.convertToResourceId(this), container, false);
    }
}
