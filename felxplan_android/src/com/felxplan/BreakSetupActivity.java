package com.felxplan;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.flexplan.common.WorkBreakFactory;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateConverter;

public class BreakSetupActivity extends AbstractActivity {

	private WorkBreak currentBreak;
	private long currentDate;
	private TimePicker timeFrom;
	private TimePicker timeTo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_break_setup);

		currentDate = getIntent().getExtras().getLong("currentDate");
		
		timeFrom = (TimePicker) findViewById(R.id.break_time_from);
		timeTo = (TimePicker) findViewById(R.id.break_time_to);
		
		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);

		TextView dateView = (TextView) findViewById(R.id.current_date);
		dateView.setText(DateConverter.convertToDate(currentDate));

		Button saveButton = (Button) findViewById(R.id.save_break_button);
		saveButton.setOnClickListener(new SaveBreakListener(
				((FlexplanApplication) getApplication()).getDbHelper(),
				getCurrentBreak(), currentDate));
		saveButton.setOnTouchListener(mDelayHideTouchListener);
	}

	private WorkBreak getCurrentBreak() {
		if(currentBreak == null){
			currentBreak = WorkBreakFactory.createWorkBreak(getTimeFrom(), getTimeTo());
		}else{
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

}
