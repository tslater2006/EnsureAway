package com.ensureaway.entities;

import org.kroz.activerecord.ActiveRecordBase;

public class Policy extends ActiveRecordBase {
	public final int SUNDAY = 0x01;
	public final int MONDAY = 0x10;
	public final int TUESDAY = 0x100;
	public final int WEDNESDAY = 0x1000;
	public final int THURSDAY = 0x10000;
	public final int FRIDAY = 0x100000;
	public final int SATURDAY = 0x1000000;
	
	public final int ACTION_REPORT = 0x01;
	public final int ACTION_LOG = 0x02;
	
	public String name;
	public String description;
	public String action;

	public int startHour;
	public int startMinute;

	public int endHour;
	public int endMinute;

	public int days;

	public boolean active;

	@Override
	public String toString() {
		return "";
	}

	public Policy() {
	}

}
