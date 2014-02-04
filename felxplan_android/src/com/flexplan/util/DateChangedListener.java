package com.flexplan.util;

import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

import com.flexplan.DateFieldProvider;

public class DateChangedListener implements OnDateChangeListener {

	private DateFieldProvider dateFieldProvider;

	public DateChangedListener(DateFieldProvider dateFieldProvider) {
		this.dateFieldProvider = dateFieldProvider;
	}


	@Override
	public void onSelectedDayChange(CalendarView paramCalendarView,
			int day, int month, int year) {
		dateFieldProvider.updateDateField(day, month, year);
	}

}
