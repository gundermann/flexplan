package com.flexplan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TimePicker;

import com.flexplan.common.util.DateHelper;
import com.flexplan.util.AbstractDialog;
import com.flexplan.util.SetTimeOnFlextimeDayListener;

public class FlextimeTimeSetupDialog extends AbstractDialog implements
		TimeSetup {

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
		timeFrom.setIs24HourView(true);
		setupTimeValues(timeFrom, "starttime", flextimeDaySetup
				.getFlextimeDay().getStartTime());
		timeTo = (TimePicker) view.findViewById(R.id.timeTo);
		timeTo.setIs24HourView(true);
		setupTimeValues(timeTo, "endtime", flextimeDaySetup.getFlextimeDay()
				.getEndTime());
	}

	private void setupTimeValues(TimePicker timePicker, String pref,
			long settedTime) {
		if (settedTime == DateHelper.DAY_START
				|| settedTime == DateHelper.DAY_END) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getActivity());
			timePicker.setCurrentHour(DateHelper.convertHoursLongToInt(prefs
					.getLong(pref, 0L)));
			timePicker.setCurrentMinute(DateHelper
					.convertMinutesLongToInt(prefs.getLong(pref, 0L)));
		} else {
			timePicker.setCurrentHour(DateHelper
					.convertHoursLongToInt(settedTime));
			timePicker.setCurrentMinute(DateHelper
					.convertMinutesLongToInt(settedTime));
		}
	}

	protected Dialog buildDialog(View view) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getActivity());
		dialogBuilder.setView(view);
		dialogBuilder.setPositiveButton(android.R.string.ok,
				new SetTimeOnFlextimeDayListener(this, flextimeDaySetup));
		dialogBuilder.setNegativeButton(android.R.string.cancel, null);
		return dialogBuilder.create();
	}

	protected View getCurrentView() {
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.timerange_picker, null);
		return view;
	}

	public long getCurrentStartTime() {
		return DateHelper.convertHoursToLong(timeFrom.getCurrentHour())
				+ DateHelper.convertMinutesToLong(timeFrom.getCurrentMinute());
	}

	public long getCurrentEndTime() {
		return DateHelper.convertHoursToLong(timeTo.getCurrentHour())
				+ DateHelper.convertMinutesToLong(timeTo.getCurrentMinute());
	}
}
