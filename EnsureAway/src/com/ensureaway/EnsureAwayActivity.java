package com.ensureaway;

import com.ensureaway.receivers.ScreenReceiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class EnsureAwayActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(this, "Testing GitHub Push", Toast.LENGTH_LONG).show();
        ScreenReceiver sr = new ScreenReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.SCREEN_ON");
		filter.addAction("android.intent.action.SCREEN_OFF");
		registerReceiver(sr,filter);
    }
}