package com.felxplan;

import java.util.List;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.flexplan.common.FlextimeDayFactory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

public class FlextimeDaySetupActivity extends AbstractActivity {

	private FlextimeDay currentFlextimeDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_flextime_day_setup);

		controlsView = findViewById(R.id.fullscreen_content_controls);
		contentView = findViewById(R.id.fullscreen_content);

		final TimePicker timeFrom = (TimePicker) findViewById(R.id.timeFrom);
		final TimePicker timeTo = (TimePicker) findViewById(R.id.timeTo);

		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);

		Button addBreakButton = (Button) findViewById(R.id.break_view_button);
		addBreakButton.setOnClickListener(new ShowBreakListener(
				getApplicationContext()));

		Button saveButton = (Button) findViewById(R.id.save_flextimeday_button);
		saveButton.setOnClickListener(new SaveFlextimeDayListener(
				getCurrentFlextimeDay(),
				((FlexplanApplication) getApplication()).getDbHelper()));

		findViewById(R.id.break_view_button).setOnTouchListener(
				mDelayHideTouchListener);
	}

	private FlextimeDay getCurrentFlextimeDay() {
		saveFlextime();
		return currentFlextimeDay;
	}

	private void saveFlextime() {
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
		// TODO Auto-generated method stub
		return null;
	}

	private long getEndTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	private long getStartTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	private long getDate() {
		// TODO Auto-generated method stub
		return 0;
	}
}
