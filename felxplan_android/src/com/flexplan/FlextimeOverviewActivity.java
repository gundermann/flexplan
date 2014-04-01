package com.flexplan;

import java.util.GregorianCalendar;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.util.DateHelper;
import com.flexplan.setup.ChangeProvider;
import com.flexplan.setup.day.FlextimeDaySetupActivity;
import com.flexplan.util.DeleteProvider;
import com.flexplan.util.SimpleDialog;
import com.flexplan.util.WeekChangeListener;

public class FlextimeOverviewActivity extends AbstractFlextimeActivity
		implements DeleteProvider<FlextimeDay>, ChangeProvider<FlextimeDay> {

	private int currentWeek;

	private int currentYear;

	private TextView week;

	private ImageButton prevWeekBt;

	private ImageButton nextWeekBt;

	private TextView hoursThisWeekTv;

	private ListView flextimeWeekList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.title_activity_flextime_overview);
		setupWeek();
		updateWeekView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getCacheDbHelper().cleanup();
		updateListView();
	}

	private void updateWeekView() {
		week.setText("KW " + currentWeek + " " + currentYear);
	}

	private void updateListView() {
		flextimeWeekList.setAdapter(new FlextimeOverviewAdapter(
				getApplicationContext(), getCurrentWeekDays()));
		flextimeWeekList.setEmptyView(findViewById(R.id.empty));
		flextimeWeekList.setOnItemClickListener(ListenerFactory
				.createOnChangeFlextimeListener(this));
		flextimeWeekList.setOnItemLongClickListener(ListenerFactory
				.createDeleteClickListener(this));
		updateHours();
	}

	private void updateHours() {
		long hours = 0;
		for (FlextimeDay day : getCurrentWeekDays()) {
			hours += day.getLenght();
			if (day.getWorkBreaks().isEmpty()) {
				hours -= prefs.getLong("breaktime", 0);
			}
		}
		hoursThisWeekTv.setText(DateHelper.getTimeAsString(hours));

		if (hours < prefs.getLong("hours_per_week", 0)) {
			hoursThisWeekTv.setTextColor(Color.RED);
		} else {
			hoursThisWeekTv.setTextColor(Color.GREEN);
		}
	}

	private List<FlextimeDay> getCurrentWeekDays() {
		return getFlextimeDbHelper().getCurrentWeekDays(currentWeek,
				currentYear);
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
		hoursThisWeekTv = (TextView) findViewById(R.id.hours_this_week);
		flextimeWeekList = (ListView) findViewById(R.id.flextime_overview);
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
		if (newWeek < 1) {
			currentWeek = 52;
			currentYear--;
		} else if (newWeek > 52) {
			currentWeek = 1;
			currentYear++;
		} else {
			currentWeek = newWeek;
		}
		updateWeekView();
		updateListView();
	}

	@Override
	protected int getMenu() {
		return R.menu.flextime_overview_menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.setup_flextime_day:
			startNextActivity(FlextimeDaySetupActivity.class);
			break;
		case R.id.preferences:
			startNextActivity(SettingsActivity.class);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void delete(FlextimeDay flextimeDay) {
		getFlextimeDbHelper().delete(flextimeDay);
		updateListView();
	}

	@Override
	public void initDelete(FlextimeDay flextimeDay) {
		SimpleDialog
				.newInstance(
						ListenerFactory.createFlextimeDeleteListener(this,
								flextimeDay),
						getString(R.string.ask_delete_day)).show(
						getSupportFragmentManager(), TAG);
	}

	@Override
	public void initChange(FlextimeDay flextimeDay) {
		setFlextimeDay(flextimeDay);
		updateCache();
		startNextActivity(FlextimeDaySetupActivity.class);
	}

}
