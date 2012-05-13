package com.ensureaway.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ensureaway.activities.LockActivity;

public class LockService extends Service {

	final String LOG_TAG = "LockService";
	static LockService lockService = null;
	
	public static LockService getInstance()
	{
		return lockService;
	}
	
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Service created...", Toast.LENGTH_LONG).show();
		Log.i(LOG_TAG, "Service created...");
		lockService = this;
	}

	@Override
	public int onStartCommand(Intent i, int flags, int startId)
	{
		return START_STICKY;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i(LOG_TAG, "Service started...");
		lockService = this;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service destroyed...", Toast.LENGTH_LONG).show();
		lockService = null;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	public void lockDevice(String reason)
	{
		Intent i = new Intent(this,LockActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("reason", reason);
		startActivity(i);
	}
}
