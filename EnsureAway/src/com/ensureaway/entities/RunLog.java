package com.ensureaway.entities;

import java.util.Calendar;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="RunLog")
public class RunLog extends Model {

	@Column(name="time")
	public Calendar time;
	
	public RunLog()
	{
		//time = Calendar.getInstance();
	}
}
