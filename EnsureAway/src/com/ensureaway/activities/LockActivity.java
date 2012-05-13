package com.ensureaway.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ensureaway.R;

public class LockActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.lockscreen);
	    // TODO Auto-generated method stub
	    Bundle extras = getIntent().getExtras();
	    String reason = extras.getString("reason");
	    TextView tv = (TextView)findViewById(R.id.lockScreenReason);
	    tv.setText(reason);
	    
	    Button b = (Button)findViewById(R.id.lockScreenUnlockButton);
	    b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	    
	}

}
