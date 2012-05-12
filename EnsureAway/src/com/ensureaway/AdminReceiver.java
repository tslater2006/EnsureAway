package com.ensureaway;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AdminReceiver extends DeviceAdminReceiver {
	final String LOG_TAG = "EnsureAway";
	@Override
	public void onReceive(Context context, Intent intent) {
	    // TODO Auto-generated method stub
		Log.d(LOG_TAG,"Admin Receiver Called");
	}

}
