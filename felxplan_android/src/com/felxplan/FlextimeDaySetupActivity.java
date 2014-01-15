package com.felxplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.flexplan.common.FlextimeDayFactory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

public class FlextimeDaySetupActivity extends AbstractActivity implements FlextimeDaySetup{

	private FlextimeDay currentFlextimeDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final TimePicker timeFrom = (TimePicker) findViewById(R.id.timeFrom);
		final TimePicker timeTo = (TimePicker) findViewById(R.id.timeTo);

		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);

		Button addBreakButton = (Button) findViewById(R.id.break_view_button);
		addBreakButton.setOnClickListener(new ShowBreakListener(
				this, this));

		Button saveButton = (Button) findViewById(R.id.save_flextimeday_button);
		saveButton.setOnClickListener(new SaveFlextimeDayListener(
				this,
				((FlexplanApplication) getApplication()).getDbHelper()));

		findViewById(R.id.break_view_button).setOnTouchListener(
				mDelayHideTouchListener);
		findViewById(R.id.save_flextimeday_button).setOnTouchListener(
				mDelayHideTouchListener);
	}

	@Override
	public void saveFlextimeDay() {
		if (currentFlextimeDay == null
				|| currentFlextimeDay.getDate() != getDate()) {
			currentFlextimeDay = FlextimeDayFactory.createFlextimeDay(
					getDate(), getStartTime(), getEndTime(), getBreaks());
		} else {
			currentFlextimeDay.setStartTime(getStartTime());
			currentFlextimeDay.setEndTime(getEndTime());
			for (WorkBreak breakTime : getBreaks()) {
				currentFlextimeDay.addBreak(breakTime);
			}
		}

	}

	private List<WorkBreak> getBreaks() {
		List<WorkBreak> breakList = new ArrayList<WorkBreak>();
		breakList.addAll(currentFlextimeDay.getWorkBreaks());
		return breakList;
	}

	private long getEndTime() {
		//TODO
		return 0L;
	}

	private long getStartTime() {
		//TODO
		return 0L;
	}

	private long getDate() {
		//TODO
		return 0L;
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_flextime_day_setup);		
	}

	public FlextimeDay getFlextimeDay() {
		saveFlextimeDay();
		return currentFlextimeDay;
	}
}
