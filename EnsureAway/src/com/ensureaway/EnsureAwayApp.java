package com.ensureaway;

import java.io.File;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.ensureaway.entities.RunLog;
import com.ensureaway.receivers.AdminReceiver;

public class EnsureAwayApp extends Application {
	public static DevicePolicyManager mDPM;
	public static ComponentName mAdminName;
	public static boolean IS_FIRST_RUN = false;
	public static boolean passed_login = false;
	
	public void onCreate() {
		super.onCreate();
		
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
		IS_FIRST_RUN = new Select().from(RunLog.class).execute().size() == 0;
		mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
		mAdminName = new ComponentName(this, AdminReceiver.class);
				
	}

	public void onTerminate() {
		ActiveAndroid.dispose();
		super.onTerminate();
	}
}
