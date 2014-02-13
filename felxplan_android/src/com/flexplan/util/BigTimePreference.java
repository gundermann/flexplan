package com.flexplan.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.flexplan.R;

public class BigTimePreference extends TimePreference {

	private NumberPicker hourPicker;
	private NumberPicker minutePicker;

	public BigTimePreference(Context ctxt, AttributeSet attrs) {
		super(ctxt, attrs);
	}

	@Override
	protected View onCreateDialogView() {
		LinearLayout linlay = new LinearLayout(getContext());
		((LayoutInflater) getContext()
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.timepicker, linlay);
		
		hourPicker = (NumberPicker) linlay.findViewById(R.id.hours);
		hourPicker.setMaxValue(100);
		minutePicker = (NumberPicker) linlay.findViewById(R.id.minutes);
		minutePicker.setMaxValue(59);
		return linlay;
	}

	@Override
	protected void bindspecificDialogView() {
		hourPicker.setValue(lastHour);
		minutePicker.setValue(lastMinute);
	}

	protected int getCurrentMinute() {
		return minutePicker.getValue();
	}

	protected int getCurrentHour() {
		return hourPicker.getValue();
	}
}
