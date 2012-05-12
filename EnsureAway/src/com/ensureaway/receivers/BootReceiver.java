package com.ensureaway.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

	final String LOG_TAG = "EnsureAway";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG,"Boot Receiver Called");
	}

}
