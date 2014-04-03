package com.flexplan.persistence;

import com.flexplan.common.util.DateHelper;
import com.flexplan.setup.TimeSetup;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;

public class StartTimeSetupListener implements OnClickListener,
		OnTimeSetListener {

	private TimeSetup flextimeDaySetup;
	private Context context;

	public StartTimeSetupListener(Context context, TimeSetup flextimeDaySetup) {
		this.context = context;
		this.flextimeDaySetup = flextimeDaySetup;
	}

	@Override
	public void onClick(View view) {
		long defaultTime = getTimeValues("starttime", flextimeDaySetup
				.getStartTime());
		new TimePickerDialog(context, this,
				DateHelper.convertHoursLongToInt(defaultTime),
				DateHelper.convertMinutesLongToInt(defaultTime), true).show();
	}

	private long getTimeValues(String pref, long settedTime) {
		if (settedTime == DateHelper.DAY_START) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(context);
			return prefs.getLong(pref, 0L);
		} else {
			return settedTime;
		}
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		flextimeDaySetup.setTime(DateHelper.convertHoursAndMinutesToLong(hourOfDay, minute), flextimeDaySetup
				.getEndTime());
	}
}
