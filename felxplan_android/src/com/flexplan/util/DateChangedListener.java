package com.flexplan.util;

import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

import com.flexplan.FlextimeDaySetup;

public class DateChangedListener implements OnDateChangeListener {

	private FlextimeDaySetup setup;

	public DateChangedListener(FlextimeDaySetup setup) {
		this.setup = setup;
	}


	@Override
	public void onSelectedDayChange(CalendarView paramCalendarView,
			int year, int month, int day) {
		setup.updateDate(day, month, year);
	}

}
