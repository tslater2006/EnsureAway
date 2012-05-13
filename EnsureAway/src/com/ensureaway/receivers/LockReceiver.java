package com.ensureaway.receivers;

import com.ensureaway.activities.LockActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LockReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent i = new Intent(context,LockActivity.class);
		String reason = intent.getExtras().getString("reason");
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("reason", reason);
		context.startActivity(i);
	}

}
