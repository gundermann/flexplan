package com.flexplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.flexplan.common.WorkBreakFactory;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;

public class BreakSetupActivity extends AbstractActivity {

	private WorkBreak currentBreak;
	private long currentDate;
	private TimePicker timeFrom;
	private TimePicker timeTo;
	private Button saveBreakBt;
	private TextView dateView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentDate = getIntent().getExtras().getLong("currentDate");
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

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_break_setup);		
	}

	@Override
	public List<View> getViewsForDelayedHide() {
		List<View> views = new ArrayList<View>();
		views.add(saveBreakBt);
		return views;
	}

	@Override
	protected void initElements() {
		timeFrom = (TimePicker) findViewById(R.id.break_time_from);
		timeTo = (TimePicker) findViewById(R.id.break_time_to);
		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);
		dateView = (TextView) findViewById(R.id.current_date);
		dateView.setText(DateHelper.convertToDate(currentDate));
		saveBreakBt = (Button) findViewById(R.id.save_break_button);
		saveBreakBt.setOnClickListener(new SaveBreakListener(
				((FlexplanApplication) getApplication()).getDbHelper(),
				getCurrentBreak(), currentDate));		
	}

}
