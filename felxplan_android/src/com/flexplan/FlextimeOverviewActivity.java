package com.flexplan;

import java.util.GregorianCalendar;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.persistence.FlextimeDB;
import com.flexplan.util.AbstractActivity;
import com.flexplan.util.WeekChangeListener;

public class FlextimeOverviewActivity extends AbstractActivity {

	private int currentWeek;

	private int currentYear;

	private TextView week;

	private FlextimeDB dbHelper;

	private ImageButton prevWeekBt;

	private ImageButton nextWeekBt;

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
				getApplicationContext(), getCurrentWeekDays()));
	}

	private List<FlextimeDay> getCurrentWeekDays() {
		if (dbHelper == null) {
			setDbHelper(((FlexplanApplication) getApplication()).getDbHelper());
		}
		return dbHelper.getCurrentWeekDays(currentWeek, currentYear);
	}

	public void setDbHelper(FlextimeDB dbHelper) {
		this.dbHelper = dbHelper;
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
	protected void initElements() {
		week = (TextView) findViewById(R.id.week);
		prevWeekBt = (ImageButton) findViewById(R.id.prev_week);
		nextWeekBt = (ImageButton) findViewById(R.id.next_week);
		prevWeekBt.setOnClickListener(new WeekChangeListener(this, -1));
		nextWeekBt.setOnClickListener(new WeekChangeListener(this, +1));
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public int getCurrentWeek() {
		return currentWeek;
	}

	public void setCurrentWeek(int newWeek) {
		currentWeek = newWeek;
		updateWeekView();
	}

	@Override
	protected int getMenu() {
		return R.menu.flextime_overview_menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.setup_flextime_day:
			startNextActivity(new Intent(getApplicationContext(),
					FlextimeDaySetupActivity.class));
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
