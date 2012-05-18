package com.ensureaway.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ensureaway.EnsureAwayApp;
import com.ensureaway.R;
import com.ensureaway.entities.Password;
import com.ensureaway.entities.Policy;
import com.ensureaway.entities.Settings;

public class AdminPanelActivity extends Activity {
	private Settings currentSettings = null;
	private EditText emailAddress;
	private EditText phoneNumber;
	private Spinner reportFrequency;
	private Spinner reportType;
	private TextView activePolicies;
	private Button saveSettings;
	private Button changePassword;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!Password.validatePassword(getIntent().getExtras().getString("passHash"))) {
			finish();
		}
		
		if (EnsureAwayApp.passed_login == false) {
			finish();
		}
		setContentView(R.layout.adminpanel);

		// get active settings:
		currentSettings = Settings.getCurrentSettings();

		// get and setup views
		emailAddress = (EditText) findViewById(R.id.email);
		phoneNumber = (EditText) findViewById(R.id.phone);
		reportFrequency = (Spinner) findViewById(R.id.reportFreq);
		reportType = (Spinner) findViewById(R.id.reportType);
		activePolicies = (TextView) findViewById(R.id.activepolicies);
		saveSettings = (Button) findViewById(R.id.savesettings);
		changePassword = (Button) findViewById(R.id.changepassword);
		// setup adapters for spinners
		ArrayAdapter<CharSequence> freq_adapter = ArrayAdapter.createFromResource(this, R.array.report_frequency,android.R.layout.simple_spinner_item);
		freq_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		reportFrequency.setAdapter(freq_adapter);

		ArrayAdapter<CharSequence> type_adapter = ArrayAdapter.createFromResource(this, R.array.report_type,android.R.layout.simple_spinner_item);
		type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		reportType.setAdapter(type_adapter);

		// set values
		emailAddress.setText(currentSettings.email);
		phoneNumber.setText(currentSettings.phone);
		reportFrequency.setSelection(currentSettings.reportFrequency);
		reportType.setSelection(currentSettings.reportType);

		int activePolicyCount = Policy.getActiveCount();
		activePolicies.setText(activePolicyCount + "");
		
		// Setup OnClick Listeners
		
		saveSettings.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveSettings();
				Toast.makeText(AdminPanelActivity.this, "Settings Saved", Toast.LENGTH_SHORT).show();
			}
		});
		
		changePassword.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				makeChangePasswordDialog().show();
			}
		});
		
		// TODO Auto-generated method stub
	}

	private AlertDialog makeChangePasswordDialog()
	{
		LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.changepassword, null);
        final EditText et = (EditText)textEntryView.findViewById(R.id.password_edit);
        return new AlertDialog.Builder(AdminPanelActivity.this)
            .setTitle("Change Password")
            .setView(textEntryView)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	
                	Password.setNewPassword(et.getText().toString());
                	Toast.makeText(AdminPanelActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();
                    /* User clicked OK so do some stuff */
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked cancel so do some stuff */
                }
            })
            .create();
	}
	private void saveSettings()
	{
		currentSettings.email = emailAddress.getText().toString();
		currentSettings.phone = phoneNumber.getText().toString();
		currentSettings.reportFrequency = reportFrequency.getSelectedItemPosition();
		currentSettings.reportType = reportType.getSelectedItemPosition();
		currentSettings.save();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		saveSettings();
		Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
	}
}
