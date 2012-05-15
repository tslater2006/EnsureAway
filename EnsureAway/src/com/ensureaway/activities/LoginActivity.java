package com.ensureaway.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.ensureaway.EnsureAwayApp;
import com.ensureaway.R;
import com.ensureaway.entities.RunLog;
import com.ensureaway.receivers.ScreenReceiver;

public class LoginActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		RunLog rl = new RunLog();
		rl.time = Calendar.getInstance();
		rl.save();

		if (!EnsureAwayApp.mDPM.isAdminActive(EnsureAwayApp.mAdminName)) {
			// try to become active – must happen here in this activity, to get
			// result
			Intent intent = new Intent(
					DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
					EnsureAwayApp.mAdminName);
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
					"In order for EnsureAway to operate you need to enable it as a Device Administrator.");
			startActivityForResult(intent, 23);
		}

		// Register SCREEN ON/OFF Filters
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(new ScreenReceiver(), filter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == 23)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				Toast.makeText(this, "Device Admin Enabled", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
