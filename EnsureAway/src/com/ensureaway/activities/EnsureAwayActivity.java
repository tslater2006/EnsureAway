package com.ensureaway.activities;

import org.kroz.activerecord.ActiveRecordBase;
import org.kroz.activerecord.ActiveRecordException;
import org.kroz.activerecord.Database;
import org.kroz.activerecord.DatabaseBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.ensureaway.R;
import com.ensureaway.entities.Policy;
import com.ensureaway.receivers.ScreenReceiver;

public class EnsureAwayActivity extends Activity {
	private Context ctx;
	private DatabaseBuilder builder;
	private ActiveRecordBase conn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		try {
			initDatabase();
			
			// Create a Note entry by requesting it from the database connector
			Policy ent = conn.newEntity(Policy.class);
			ent.days = ent.MONDAY | ent.TUESDAY | ent.WEDNESDAY;
			ent.action = "REPORT";
			ent.name = "Policy 1";
			ent.save();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(new ScreenReceiver(), filter);
	}

	private void initDatabase() throws ActiveRecordException {
		Log.d("EnsureAway", "initDatabase()");

		this.ctx = getApplicationContext();
		this.builder = new DatabaseBuilder("test.db");
		this.builder.addClass(Policy.class);
		// Setup the builder
		Database.setBuilder(this.builder);

		Log.d("EnsureAway", "opening ActiveRecordBase");
		this.conn = ActiveRecordBase.open(ctx, "test.db", 1);
	}
}