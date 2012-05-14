package com.ensureaway.entities;

import java.util.Calendar;

import org.kroz.activerecord.ActiveRecordBase;

public class Policy extends ActiveRecordBase {
	public final static int SUNDAY = 1;
	public final static int MONDAY = 2;
	public final static int TUESDAY = 4;
	public final static int WEDNESDAY = 8;
	public final static int THURSDAY = 16;
	public final static int FRIDAY = 32;
	public final static int SATURDAY = 64;

	public final static String ACTION_REPORT = "REPORT";
	public final static String ACTION_LOG = "LOG";

	public String name;
	public String description;
	public String action;

	public int startHour;
	public int startMinute;

	public int endHour;
	public int endMinute;

	public int days;

	public boolean active;

	private Calendar m_startTime = null;
	private Calendar m_endTime = null;

	@Override
	public String toString() {
		return "";
	}

	public Policy() {
	}

	public Policy(String name, String action, int startHour, int startMinute, int endHour, int endMinute, int days, boolean active)
	{
		this.name = name;
		this.action = action;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.days = days;
		this.active = active;
		initCalendars();
	}
	public void initCalendars() {
		Calendar now = Calendar.getInstance();

		if (m_endTime != null && m_startTime != null) {
			if (now.before(m_endTime)) {
				return;
			}
		}
		
		m_startTime = Calendar.getInstance();
		m_startTime.set(Calendar.HOUR_OF_DAY, 0);
		m_startTime.set(Calendar.MINUTE, 0);
		m_startTime.set(Calendar.SECOND, 0);
		m_startTime.set(Calendar.MILLISECOND, 0);

		m_endTime = (Calendar) m_startTime.clone();

		m_startTime.add(Calendar.HOUR_OF_DAY, startHour);
		m_startTime.add(Calendar.MINUTE, startMinute);

		m_endTime.add(Calendar.HOUR_OF_DAY, endHour);
		m_endTime.add(Calendar.MINUTE, endMinute);
		if (endHour < startHour) {
			m_endTime.add(Calendar.DAY_OF_YEAR, 1);
		}
		
	}

	public boolean isInEffect() {
		initCalendars();
		if (!active)
			return false;

		int currentWeekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		int currentWeekdayMask = 1 << (currentWeekday - 1);

		if ((days & currentWeekdayMask) == currentWeekdayMask) {
			// Rule should be run today

			Calendar now = Calendar.getInstance();
			return (now.after(m_startTime) && now.before(m_endTime));

		} else {
			return false;
		}

	}

	public String getDayString() {
		// TODO Auto-generated method stub
		final String dayChars = "SMTWTFS";
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < 7; x ++)
		{
			if (  (days & (1 << x)) == (1 << x))
			{
				sb.append(dayChars.charAt(x));
			}
			else
			{
				sb.append("-");
			}
		}
		
		return sb.toString();
	}

}
