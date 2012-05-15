package com.ensureaway;

import java.io.File;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.activeandroid.ActiveAndroid;
import com.ensureaway.receivers.AdminReceiver;

public class EnsureAwayApp extends Application {
	public static DevicePolicyManager mDPM;
	public static ComponentName mAdminName;

	public void onCreate() {
		super.onCreate();
		boolean IS_FIRST_RUN = true;
		if (IS_FIRST_RUN )
		{
			File f = new File("/data/data/com.ensureaway/databases/ensureaway.db");
			f.delete();
			f = new File("/data/data/com.ensureaway/databases/ensureaway.db-journal");
			f.delete();
			f = new File(f.getParent());
			f.delete();
		}
		ActiveAndroid.initialize(this);
		
		mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
		mAdminName = new ComponentName(this, AdminReceiver.class);
				
	}

	public void onTerminate() {
		ActiveAndroid.dispose();
		super.onTerminate();
	}
}
