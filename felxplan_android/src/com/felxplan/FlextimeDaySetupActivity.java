package com.felxplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.flexplan.common.FlextimeDayFactory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;

public class FlextimeDaySetupActivity extends AbstractActivity implements
		FlextimeDaySetup {

	private FlextimeDay currentFlextimeDay;
	private Button addBreakBt;
	private Button saveBreakBt;
	private DatePicker day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void saveFlextimeDay() {
		if (currentFlextimeDay == null
				|| currentFlextimeDay.getDate() != getDate()) {
			currentFlextimeDay = FlextimeDayFactory.createFlextimeDay(
					getDate(), DateHelper.DAY_START, DateHelper.DAY_END,
					getBreaks());
		} else {
			currentFlextimeDay.setDate(getDate());
		}
	}

	private long getDate() {
		return day.getDrawingTime();
	}

	private List<WorkBreak> getBreaks() {
		List<WorkBreak> breakList = new ArrayList<WorkBreak>();
		breakList.addAll(currentFlextimeDay.getWorkBreaks());
		return breakList;
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_flextime_day_setup);
	}

	public FlextimeDay getFlextimeDay() {
		saveFlextimeDay();
		return currentFlextimeDay;
	}

	@Override
	public List<View> getViewsForDelayedHide() {
		List<View> views = new ArrayList<View>();
		views.add(addBreakBt);
		views.add(saveBreakBt);
		return views;
	}

	@Override
	protected void initElements() {
		day = (DatePicker) findViewById(R.id.day);
		addBreakBt = (Button) findViewById(R.id.break_view_button);
		addBreakBt.setOnClickListener(new ShowBreakListener(this, this));
		saveBreakBt = (Button) findViewById(R.id.save_flextimeday_button);
		saveBreakBt.setOnClickListener(new SaveFlextimeDayListener(this,
				((FlexplanApplication) getApplication()).getDbHelper()));
	}
}
