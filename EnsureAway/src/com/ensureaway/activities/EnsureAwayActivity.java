package com.ensureaway.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.ensureaway.R;
import com.ensureaway.services.LockService;

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
				LockService ls = LockService.getInstance();
				if (ls == null)
				{
					Intent serviceIntent = new Intent(EnsureAwayActivity.this,LockService.class);
					startService(serviceIntent);
					ls = LockService.getInstance();
				}
				
				if (ls != null)
				{
					ls.lockDevice("This is my reason");
				}
			}
		});
        
    }
}