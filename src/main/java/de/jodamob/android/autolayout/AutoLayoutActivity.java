package de.jodamob.android.autolayout;

import android.app.Activity;
import android.os.Bundle;

public class AutoLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);       
        new NameConverter();
        setContentView(NameConverter.convertToResourceId(this));
    }   
}
