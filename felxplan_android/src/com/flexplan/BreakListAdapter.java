package com.flexplan;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;
import com.flexplan.persistence.StartTimeSetupListener;
import com.flexplan.setup.EndTimeSetupListener;
import com.flexplan.setup.breaks.BreakSetup;
import com.flexplan.setup.breaks.BreakTimeSetup;

public class BreakListAdapter extends ArrayAdapter<WorkBreak> {

	private List<WorkBreak> breakList;
	private BreakSetup setup;
	private Context context;

	public BreakListAdapter(Context context, List<WorkBreak> breakList,
			BreakSetup setup) {
		super(context, R.layout.break_list, breakList);
		this.context = context;
		this.breakList = breakList;
		this.setup = setup;
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
		Button timeFrom = (Button) rowView.findViewById(R.id.break_time_from);
		timeFrom.setOnClickListener(new StartTimeSetupListener(context,
				new BreakTimeSetup(workBreak, setup)));
		Button timeTo = (Button) rowView.findViewById(R.id.break_time_to);
		timeTo.setOnClickListener(new EndTimeSetupListener(context,
				new BreakTimeSetup(workBreak, setup)));
		ImageButton deleteBt = (ImageButton) rowView.findViewById(R.id.deleteBt);
		deleteBt.setOnClickListener(ListenerFactory
				.createDeleteClickListener(setup, workBreak));

		timeFrom.setText(DateHelper.getTimeAsString(workBreak.getStartTime()));
		timeTo.setText(DateHelper.getTimeAsString(workBreak.getEndTime()));
	}
}
