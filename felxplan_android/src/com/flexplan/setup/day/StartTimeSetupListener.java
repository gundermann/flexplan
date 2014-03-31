package com.flexplan.setup.day;

import com.flexplan.common.util.DateHelper;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;

public class StartTimeSetupListener implements OnClickListener,
		OnTimeSetListener {

	private FlextimeDaySetupActivity flextimeDaySetup;

	public StartTimeSetupListener(FlextimeDaySetupActivity flextimeDaySetup) {
		this.flextimeDaySetup = flextimeDaySetup;
	}

	@Override
	public void onClick(View view) {
		long defaultTime = getTimeValues("starttime", flextimeDaySetup
				.getFlextimeDay().getStartTime());
		new TimePickerDialog(flextimeDaySetup, this,
				DateHelper.convertHoursLongToInt(defaultTime),
				DateHelper.convertMinutesLongToInt(defaultTime), true).show();
	}

	private long getTimeValues(String pref, long settedTime) {
		if (settedTime == DateHelper.DAY_START) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(flextimeDaySetup);
			return prefs.getLong(pref, 0L);
		} else {
			return settedTime;
		}
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		flextimeDaySetup.updateTime(view.getDrawingTime(), flextimeDaySetup
				.getFlextimeDay().getEndTime());
	}
}
