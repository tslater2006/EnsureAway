package com.ensureaway;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class EnsureAwayActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(this, "Testing GitHub Push", Toast.LENGTH_LONG).show();
    }
}