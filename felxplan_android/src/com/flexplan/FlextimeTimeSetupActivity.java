package com.flexplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class FlextimeTimeSetupActivity extends AbstractActivity {
	
	TimePicker timeFrom;
	TimePicker timeTo;
	Button showBreakViewBt;
	Button saveFlextimeDayBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_flextime_time_setup);
	}

	@Override
	public List<View> getViewsForDelayedHide() {
		List<View> views = new ArrayList<View>();
		views.add(saveFlextimeDayBt);
		views.add(showBreakViewBt);
		return views;
	}

	@Override
	protected void initElements() {
		timeFrom = (TimePicker) findViewById(R.id.timeFrom);
		timeTo = (TimePicker) findViewById(R.id.timeTo);
		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);
		showBreakViewBt = (Button) findViewById(R.id.break_view_button);
		saveFlextimeDayBt = (Button) findViewById(R.id.save_flextimeday_button);		
	}

}
