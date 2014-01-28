package com.flexplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.flexplan.util.AbstractActivityWithExtraInput;
import com.flexplan.util.ExtraProvider;
import com.flexplan.util.NextActivityClickListener;
import com.flexplan.util.NextActivityClickListenerWithExtraInput;

public class FlextimeTimeSetupActivity extends AbstractActivityWithExtraInput implements ExtraProvider {

	TimePicker timeFrom;
	TimePicker timeTo;
	Button showBreakViewBt;
	Button saveFlextimeDayBt;
	Button setupDayBt;
	long currentDate;

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
		showBreakViewBt.setOnClickListener(new NextActivityClickListenerWithExtraInput(this,
				BreakViewActivity.class));
		setupDayBt = (Button) findViewById(R.id.setup_flextime_day);
		setupDayBt.setOnClickListener(new NextActivityClickListener(this,
				FlextimeDaySetupActivity.class));
		saveFlextimeDayBt = (Button) findViewById(R.id.save_flextimeday_button);
	}
	
	


	public Bundle getExtras() {
		Bundle extras = new Bundle();
		extras.putLong("date", getCurrentDate());
		extras.putLong("date", getCurrentStartTime());
		extras.putLong("date", getCurrentEndTime());
		return extras;
	}

	private long getCurrentDate() {
		return currentDate;
	}

	private long getCurrentStartTime() {
		return timeFrom.getDrawingTime();
	}

	private long getCurrentEndTime() {
		return timeTo.getDrawingTime();
	}

	@Override
	protected void setupExtras() {
		currentDate = getIntent().getExtras().getLong("date");
	}

}
