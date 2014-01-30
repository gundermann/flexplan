package com.flexplan.util;

import com.flexplan.DateFieldProvider;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

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
