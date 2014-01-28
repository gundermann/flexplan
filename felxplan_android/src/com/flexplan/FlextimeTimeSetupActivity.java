package com.flexplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.flexplan.util.AbstractActivity;
import com.flexplan.util.NextActivityClickListener;

public class FlextimeTimeSetupActivity extends AbstractActivity {

	TimePicker timeFrom;
	TimePicker timeTo;
	Button showBreakViewBt;
	Button saveFlextimeDayBt;
	Button setupDayBt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_flextime_time_setup);
		LinearLayout linlay = (LinearLayout) findViewById(R.id.fullscreen_content);
		getLayoutInflater().inflate(R.layout.timerange_picker, linlay);
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
		showBreakViewBt.setOnClickListener(new NextActivityClickListener(this,
				BreakViewActivity.class));
		setupDayBt = (Button) findViewById(R.id.setup_flextime_day);
		setupDayBt.setOnClickListener(new NextActivityClickListener(this,
				FlextimeDaySetupActivity.class));
		saveFlextimeDayBt = (Button) findViewById(R.id.save_flextimeday_button);
	}

}
