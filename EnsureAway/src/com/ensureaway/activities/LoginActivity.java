package com.ensureaway.activities;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.ensureaway.EnsureAwayApp;
import com.ensureaway.R;
import com.ensureaway.entities.Password;
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
		
		EnsureAwayApp.passed_login = false;
		
		if (!EnsureAwayApp.mDPM.isAdminActive(EnsureAwayApp.mAdminName)) {
			// try to become active � must happen here in this activity, to get
			// result
			Intent intent = new Intent(
					DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
					EnsureAwayApp.mAdminName);
			intent.putExtra(
					DevicePolicyManager.EXTRA_ADD_EXPLANATION,
					"In order for EnsureAway to operate you need to enable it as a Device Administrator.");
			startActivityForResult(intent, 23);

		}

		// Register SCREEN ON/OFF Filters
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(new ScreenReceiver(), filter);
		final Button b = (Button) findViewById(R.id.btnProceed);
		final EditText et = (EditText) findViewById(R.id.editText1);
		if (EnsureAwayApp.IS_FIRST_RUN) {

			b.setText("Set Password");
			b.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Password p = new Password(et.getText().toString());
					p.save();

					b.setText("Unlock");
					b.setOnClickListener(new UnlockListener(LoginActivity.this,et));
				}
			});
		} else {
			b.setOnClickListener(new UnlockListener(this, et));
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 23) {
			if (resultCode == Activity.RESULT_OK) {
				Toast.makeText(this, "Device Admin Enabled", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}

class UnlockListener implements OnClickListener {
	Context ctx;
	EditText passwordBox;

	public UnlockListener(Context context, EditText et) {
		ctx = context;
		passwordBox = et;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Password p = new Password(passwordBox.getText().toString());
		ArrayList<Password> results = new Select().from(Password.class)
				.where("hash = ? and active = ?",p.hash, 1).execute();
		if (results.size() == 1) {
			EnsureAwayApp.passed_login = true;
			
			Intent i = new Intent(ctx, AdminPanelActivity.class);
			i.putExtra("passHash", p.hash + "");
			ctx.startActivity(i);
		}
		else
		{
			Toast.makeText(ctx, "Invalid password", Toast.LENGTH_LONG).show();
			passwordBox.setText("");
		}
	}

}