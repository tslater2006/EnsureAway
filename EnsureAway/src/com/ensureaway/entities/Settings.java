package com.ensureaway.entities;

import java.util.Calendar;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name="Settings")
public class Settings extends Model {
	
	public static final int REPORT_FREQ_IMMEDIATELY = 1;
	public static final int REPORT_FREQ_HOURLY = 2;
	public static final int REPORT_FREQ_DAILY = 3;
	public static final int REPORT_FREQ_WEEKLY = 4;
	public static final int REPORT_FREQ_MONTHLY = 5;
	public static final int REPORT_FREQ_MINUTE_TEST = 6;
	
	public static final int REPORT_TYPE_FULL = 1;
	public static final int REPORT_TYPE_BASIC = 2;
	public static final int REPORT_TYPE_MINIMAL = 3;
	
	
	@Column(name="contactEmail")
	public String email = "";
	@Column(name="contactPhone")
	public String phone = "";
	@Column(name="reportFrequency")
	public int reportFrequency = 0;
	@Column(name="reportType")
	public int reportType = 0;

	@Column(name="effectiveDate")
	public Calendar effectiveDate;
	
	public static Settings getCurrentSettings()
	{
		Settings retSettings = new Select().from(Settings.class).orderBy("effectiveDate DESC").limit("1").executeSingle();
		
		if (retSettings == null)
			retSettings = new Settings();
		return retSettings;
	}
}
