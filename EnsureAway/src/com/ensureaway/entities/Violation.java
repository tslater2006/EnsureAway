package com.ensureaway.entities;

import java.util.Calendar;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="violations")
public class Violation extends Model {
	
	@Column(name="policyId")
	public int policyId;
	@Column(name="policyName")
	public int policyName;
	@Column(name="violationTime")
	public Calendar violationTime;
	@Column(name="reported")
	public boolean reported;

}
