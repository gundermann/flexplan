package com.felxplan;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.flexplan.common.business.FlextimeDay;

public class FlextimeOverviewActivity extends AbstractActivity {

	private int currentWeek;

	private int currentYear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupWeek();
		updateWeekView();

		findViewById(R.id.add_flextime_button).setOnTouchListener(
				mDelayHideTouchListener);

		updateListView();
	}

	private void updateWeekView() {
		TextView week = (TextView) findViewById(R.id.week);
		week.setText("KW " + currentWeek + " " + currentYear);
	}

	private void updateListView() {
		ListView flextimeWeekList = (ListView) findViewById(R.id.flextime_overview);
		flextimeWeekList.setAdapter(new FlextimeOverviewAdapter(
				getApplicationContext(), new ArrayList<FlextimeDay>()));
	}

	private void setupWeek() {
		GregorianCalendar cal = new GregorianCalendar();
		currentWeek = cal.get(GregorianCalendar.WEEK_OF_YEAR);
		currentYear = cal.get(GregorianCalendar.YEAR);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_flextime_overview);		
	}

}
