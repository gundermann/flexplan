package com.flexplan.setup.day;

import com.flexplan.common.util.DateHelper;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;

public class EndTimeSetupListener implements OnClickListener, OnTimeSetListener {

	private FlextimeDaySetupActivity flextimeDaySetup;

	public EndTimeSetupListener(FlextimeDaySetupActivity flextimeDaySetup) {
		this.flextimeDaySetup = flextimeDaySetup;
	}

	@Override
	public void onClick(View view) {
		long defaultTime = getTimeValues("endtime", flextimeDaySetup
				.getFlextimeDay().getEndTime());
		new TimePickerDialog(flextimeDaySetup, this,
				DateHelper.convertHoursLongToInt(defaultTime),
				DateHelper.convertMinutesLongToInt(defaultTime), true).show();
	}

	private long getTimeValues(String pref, long settedTime) {
		if (settedTime == DateHelper.DAY_END) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(flextimeDaySetup);
			return prefs.getLong(pref, 0L);
		} else {
			return settedTime;
		}
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		flextimeDaySetup.updateTime(flextimeDaySetup.getFlextimeDay()
				.getStartTime(), view.getDrawingTime());
	}

}
