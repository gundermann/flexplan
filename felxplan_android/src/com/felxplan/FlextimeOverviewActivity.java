package com.felxplan;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.flexplan.common.business.FlextimeDay;

public class FlextimeOverviewActivity extends AbstractActivity {

	private int currentWeek;

	private int currentYear;

	private Button addFlextimeBt;

	private TextView week;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupWeek();
		updateWeekView();
		updateListView();
	}

	private void updateWeekView() {
		week.setText("KW " + currentWeek + " " + currentYear);
	}

	private void updateListView() {
		ListView flextimeWeekList = (ListView) findViewById(R.id.flextime_overview);
		flextimeWeekList.setAdapter(new FlextimeOverviewAdapter(
				getApplicationContext(), getCurrentWeek()));
	}

	private List<FlextimeDay> getCurrentWeek() {
		return ((FlexplanApplication) getApplication()).getDbHelper().getCurrentWeek(currentWeek, currentYear);
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

	@Override
	public List<View> getViewsForDelayedHide() {
		List<View> views = new ArrayList<View>();
		views.add(addFlextimeBt);
		return views;
	}

	@Override
	protected void initElements() {
		week = (TextView) findViewById(R.id.week);
		addFlextimeBt = (Button) findViewById(R.id.add_flextime_button);		
	}
}
