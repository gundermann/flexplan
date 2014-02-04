package com.flexplan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.TimePicker;

import com.flexplan.util.AbstractDialog;
import com.flexplan.util.SetTimeOnFlextimeDayListener;

public class FlextimeTimeSetupDialog extends AbstractDialog implements TimeSetup {

	TimePicker timeFrom;
	TimePicker timeTo;
	private FlextimeDaySetup flextimeDaySetup;

	public static FlextimeTimeSetupDialog newInstance(
			FlextimeDaySetup flextimeDaySetup) {
		FlextimeTimeSetupDialog dialog = new FlextimeTimeSetupDialog();
		dialog.flextimeDaySetup = flextimeDaySetup;
		return dialog;
	}

	protected void instantiateViews(View view) {
		timeFrom = (TimePicker) view.findViewById(R.id.timeFrom);
		timeTo = (TimePicker) view.findViewById(R.id.timeTo);
		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);
	}

	protected Dialog buildDialog(View view) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getActivity());
		dialogBuilder.setView(view);
		dialogBuilder.setPositiveButton(android.R.string.ok, new SetTimeOnFlextimeDayListener(this, flextimeDaySetup));
		dialogBuilder.setNegativeButton(android.R.string.cancel, null);
		return dialogBuilder.create();
	}

	protected View getCurrentView() {
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.timerange_picker, null);
		return view;
	}
	
	public long getCurrentStartTime() {
		return timeFrom.getCurrentHour()*60*60*1000+timeFrom.getCurrentMinute()*60*1000;
	}

	public long getCurrentEndTime() {
		return timeTo.getCurrentHour()*60*60*1000+timeTo.getCurrentMinute()*60*1000;
	}
}
