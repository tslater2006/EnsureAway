package com.ensureaway.adapters;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ensureaway.R;
import com.ensureaway.entities.Policy;

public class PolicyAdapter extends ArrayAdapter<Policy> {

	private Context _context;
	private int layoutResource;
	private List<Policy> _policyList;

	public PolicyAdapter(Context context, int resource, List<Policy> objects) {
		super(context, resource, objects);

		_context = context;
		layoutResource = resource;
		_policyList = objects;
		
	}

	public PolicyAdapter(Context context, int resource, Policy[] objects) {
		super(context, resource, objects);

		_context = context;
		layoutResource = resource;
		_policyList = Arrays.asList(objects);
		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View row = convertView;
		PolicyHolder holder;
		if (row == null)
		{
			LayoutInflater inflater = ((Activity)_context).getLayoutInflater();
			row = inflater.inflate(layoutResource, parent,false);
			
			holder = new PolicyHolder();
			holder.name = (TextView)row.findViewById(R.id.policyName);
			holder.days = (TextView)row.findViewById(R.id.policyDays);
			holder.action = (TextView)row.findViewById(R.id.policyAction);
			holder.start = (TextView)row.findViewById(R.id.policyStart);
			holder.end = (TextView)row.findViewById(R.id.policyEnd);
			
			row.setTag(holder);
		}
		else
		{
			holder = (PolicyHolder)row.getTag();
		}
		
		Policy p = _policyList.get(position);
		holder.name.setText(p.name);
		holder.action.setText(p.action);
		holder.start.setText(p.startHour + ":" + p.startMinute);
		holder.end.setText(p.endHour + ":" + p.endMinute);
		holder.days.setText(p.getDayString());
		
		return row;
	}
	
	static class PolicyHolder
	{
		int id;
		TextView name;
		TextView days;
		TextView action;
		TextView start;
		TextView end;
	}
}
