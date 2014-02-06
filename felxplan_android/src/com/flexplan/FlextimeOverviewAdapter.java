package com.flexplan;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.util.DateHelper;

public class FlextimeOverviewAdapter extends ArrayAdapter<FlextimeDay> {
	
	TextView dayTv;
	TextView startTv;
	TextView endTv;

	public FlextimeOverviewAdapter(Context context,
			List<FlextimeDay> flextimeDays) {
		super(context, R.layout.flextime_list, R.id.flextime_day, flextimeDays);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.flextime_list, parent,
				false);
		dayTv = (TextView) rowView.findViewById(R.id.flextime_day);
		startTv = (TextView) rowView.findViewById(R.id.flextime_start);
		endTv = (TextView) rowView.findViewById(R.id.flextime_end);
		FlextimeDay currentDay = getItem(position);
		dayTv.setText(DateHelper.getDayOfWeekByDateAsString(currentDay.getDate()));
		startTv.setText(DateHelper.getTimeAsString(currentDay.getStartTime()));
		endTv.setText(DateHelper.getTimeAsString(currentDay.getEndTime()));
		
		return rowView;
	}

}
