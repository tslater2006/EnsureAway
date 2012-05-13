package com.ensureaway.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.ensureaway.R;
import com.ensureaway.receivers.ScreenReceiver;

public class EnsureAwayActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(this, "Testing GitHub Push", Toast.LENGTH_LONG).show();
        
        ((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					Intent lockIntent = new Intent();
					lockIntent.setAction("com.ensureaway.LOCK_DEVICE");
					lockIntent.putExtra("reason","My Reason Goes Here");
					sendBroadcast(lockIntent);
			}
		});
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(new ScreenReceiver(), filter);
    }
}