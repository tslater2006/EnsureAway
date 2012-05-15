package com.ensureaway.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ListView;

import com.ensureaway.R;
import com.ensureaway.adapters.PolicyAdapter;
import com.ensureaway.entities.Policy;
import com.ensureaway.receivers.ScreenReceiver;

public class EnsureAwayActivity extends Activity {
	private Context ctx;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		

		Policy policies[] = new Policy[] {
				new Policy("School Days", Policy.ACTION_LOG, 8, 30, 16, 30,
						Policy.MONDAY | Policy.TUESDAY | Policy.WEDNESDAY, true),
				new Policy("School Days", Policy.ACTION_LOG, 8, 30, 16, 30,
						Policy.TUESDAY | Policy.WEDNESDAY | Policy.THURSDAY,
						true),
				new Policy("School Days", Policy.ACTION_LOG, 8, 30, 16, 30,
						Policy.WEDNESDAY | Policy.THURSDAY | Policy.FRIDAY,
						true) };
		
		PolicyAdapter adapter = new PolicyAdapter(this, R.layout.policyview, policies);
		
		((ListView)findViewById(R.id.listView1)).setAdapter(adapter);
	}

}