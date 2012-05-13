package com.ensureaway.receivers;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

public class AdminReceiver extends DeviceAdminReceiver {
	final String LOG_TAG = "EnsureAway";
	
	@Override
    public void onEnabled(Context context, Intent intent) {
        Toast.makeText(context, "Device Admin Enabled", Toast.LENGTH_LONG).show();
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Are you sure you want to disable this?";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        Toast.makeText(context, "Success Password", Toast.LENGTH_LONG).show();
    }
	
    public void onReceive(Context context, Intent intent)
    {
    	Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
    	DevicePolicyManager mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDPM.resetPassword("", 0);
    }
}
