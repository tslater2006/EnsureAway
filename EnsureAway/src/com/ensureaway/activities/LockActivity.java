package com.ensureaway.activities;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ensureaway.R;
import com.ensureaway.receivers.AdminReceiver;

public class LockActivity extends Activity {

	private static final int REQUEST_CODE_ENABLE_ADMIN = 1;

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
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
	    DevicePolicyManager mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName mDeviceAdmin = new ComponentName(this, AdminReceiver.class);
        if (!mDPM.isAdminActive(mDeviceAdmin))
        {
        	// request admin
        	Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdmin);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    "Make me admin now!");
            startActivityForResult(intent, REQUEST_CODE_ENABLE_ADMIN);
        }
        else
        {
        	mDPM.resetPassword("test", 0);
        	mDPM.lockNow();
        }
	    
	}

}
