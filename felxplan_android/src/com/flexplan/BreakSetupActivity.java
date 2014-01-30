package com.flexplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.flexplan.common.WorkBreakFactory;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;
import com.flexplan.util.AbortListener;
import com.flexplan.util.AbstractActivityWithExtraInput;
import com.flexplan.util.SaveBreakListener;

public class BreakSetupActivity extends AbstractActivityWithExtraInput {

	private WorkBreak currentBreak;
	private long currentDate;
	private TimePicker timeFrom;
	private TimePicker timeTo;
	private Button saveBreakBt;
	private Button abortBt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private WorkBreak getCurrentBreak() {
		if (currentBreak == null) {
			currentBreak = WorkBreakFactory.createWorkBreak(getTimeFrom(),
					getTimeTo());
		} else {
			currentBreak.setStartTime(getTimeFrom());
			currentBreak.setEndTime(getTimeTo());
		}
		return currentBreak;
	}

	private long getTimeFrom() {
		return timeFrom.getDrawingTime();
	}

	private long getTimeTo() {
		return timeTo.getDrawingTime();
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_break_setup);
		LinearLayout linlay = (LinearLayout) findViewById(R.id.fullscreen_content);
		getLayoutInflater().inflate(R.layout.timerange_picker, linlay);
	}

	@Override
	public List<View> getViewsForDelayedHide() {
		List<View> views = new ArrayList<View>();
		views.add(saveBreakBt);
		return views;
	}

	@Override
	protected void initElements() {
		timeFrom = (TimePicker) findViewById(R.id.timeFrom);
		timeTo = (TimePicker) findViewById(R.id.timeTo);
		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);
		saveBreakBt = (Button) findViewById(R.id.save_break_button);
		saveBreakBt.setOnClickListener(new SaveBreakListener(
				((FlexplanApplication) getApplication()).getDbHelper(),
				getCurrentBreak(), currentDate));
		abortBt = (Button) findViewById(R.id.abort);
		abortBt.setOnClickListener(new AbortListener(this));
		this.setTitle(getTitle() + " - "+ DateHelper.getDateAsString(currentDate));
	}

	@Override
	protected void setupExtras() {
			currentDate = getIntent().getExtras().getLong("currentDate");
	}

}
