package com.flexplan;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;

public class BreakListAdapter extends ArrayAdapter<WorkBreak> {

	private TreeSet<WorkBreak> breakSet;
	private Context context;

	public BreakListAdapter(Context context, TreeSet<WorkBreak> breakList) {
		super(context, R.layout.break_list, R.id.break_time_sum);
		this.context = context;
		this.breakSet= breakList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.break_list, parent, false);
		List<WorkBreak> breakList = new ArrayList<WorkBreak>();
		breakList.addAll(breakSet);
		
		setRow(rowView, breakList.get(position));
		
		return rowView;
	}

	private void setRow(View rowView, WorkBreak workBreak) {
		TextView timeFrom = (TextView) rowView.findViewById(R.id.break_time_from);
		TextView timeTo = (TextView) rowView.findViewById(R.id.break_time_to);
		TextView timeSum = (TextView) rowView.findViewById(R.id.break_time_sum);
		
		timeFrom.setText(DateHelper.getDateAsString(workBreak.getStartTime()));
		timeTo.setText(DateHelper.getDateAsString(workBreak.getEndTime()));
		timeSum.setText(DateHelper.getDateAsString(workBreak.getLength()));
	}


}
