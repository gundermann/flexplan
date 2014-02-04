package com.flexplan;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;

public class BreakListAdapter extends ArrayAdapter<WorkBreak> {

	private List<WorkBreak> breakList;

	public BreakListAdapter(Context context, List<WorkBreak> breakList) {
		super(context, R.layout.break_list, R.id.break_time_sum, breakList);
		this.breakList = breakList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.break_list, parent, false);
		setRow(rowView, breakList.get(position));

		return rowView;
	}

	private void setRow(View rowView, WorkBreak workBreak) {
		TextView timeFrom = (TextView) rowView
				.findViewById(R.id.break_time_from);
		TextView timeTo = (TextView) rowView.findViewById(R.id.break_time_to);
		TextView timeSum = (TextView) rowView.findViewById(R.id.break_time_sum);

		timeFrom.setText(DateHelper.getTimeAsString(workBreak.getStartTime()));
		timeTo.setText(DateHelper.getTimeAsString(workBreak.getEndTime()));
		timeSum.setText(DateHelper.getTimeAsString(workBreak.getLength()));
	}

}
