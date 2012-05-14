package com.ensureaway.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {
	final String LOG_TAG = "EnsureAway";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "Screen Receiver Called");
		if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			Log.d(LOG_TAG, "SCREEN_ON");
			
			if (LockReceiver.isLocked == false) {
				/*Intent lockIntent = new Intent();
				lockIntent.setAction("com.ensureaway.LOCK_DEVICE");
				lockIntent.putExtra("reason", "My Reason Goes Here");
				context.sendBroadcast(lockIntent);*/
			}
		} else {
			Log.d(LOG_TAG, "SCREEN_OFF");
		}
	}

}
