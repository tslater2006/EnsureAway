package com.ensureaway.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.ensureaway.EnsureAwayApp;
import com.ensureaway.R;
import com.ensureaway.entities.Password;

public class AdminPanelActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if ((new Select().from(Password.class).where("hash = ? and active = ?",
				getIntent().getExtras().getString("passHash"), 1).execute()).size() != 1) {
			finish();
		}
		if (EnsureAwayApp.passed_login == false) {
			finish();
		}
		setContentView(R.layout.main);
		// TODO Auto-generated method stub
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
	}
}
