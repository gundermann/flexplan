package com.flexplan.setup;

import com.flexplan.SettingsActivity;
import com.flexplan.common.util.DateHelper;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;

public class EndTimeSetupListener implements OnClickListener, OnTimeSetListener {

	private TimeSetup flextimeDaySetup;
	private Context context;

	public EndTimeSetupListener(Context context, TimeSetup flextimeDaySetup) {
		this.context = context;
		this.flextimeDaySetup = flextimeDaySetup;
	}

	@Override
	public void onClick(View view) {
		long defaultTime = getTimeValues(SettingsActivity.END_TIME,
				flextimeDaySetup.getEndTime());
		new TimePickerDialog(context, this,
				DateHelper.convertHoursLongToInt(defaultTime),
				DateHelper.convertMinutesLongToInt(defaultTime), true).show();
	}

	private long getTimeValues(String pref, long settedTime) {
		if (settedTime == DateHelper.DAY_END) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(context);
			return prefs.getLong(pref, 0L);
		} else {
			return settedTime;
		}
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		flextimeDaySetup.setTime(flextimeDaySetup.getStartTime(),
				DateHelper.convertHoursAndMinutesToLong(hourOfDay, minute));
	}
}
