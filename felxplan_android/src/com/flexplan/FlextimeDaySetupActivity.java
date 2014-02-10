package com.flexplan;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;
import com.flexplan.util.AbstractActivityExtraProvider;
import com.flexplan.util.DateChangedListener;
import com.flexplan.util.OverwriteDialog;
import com.flexplan.util.OverwriteProvider;
import com.flexplan.util.SaveOrDiscardDialog;

public class FlextimeDaySetupActivity extends AbstractActivityExtraProvider
		implements FlextimeDaySetup, SaveDiscardProvider, OverwriteProvider {

	private static final String TAG = null;
	private FlextimeDay currentFlextimeDay;
	private DatePicker date;
	private TextView dateTv;
	private TextView timeRangeTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent().getExtras() != null
				&& getIntent().getExtras().getString("date") != null) {
			String dateString = getIntent().getExtras().getString("date");
			updateDate(Integer.valueOf(dateString.substring(0, 2)),
					Integer.valueOf(dateString.substring(3, 5)),
					Integer.valueOf(dateString.substring(6)));
		} else {
			updateDate(DateHelper.getCurrentDayOfMonth(),
					DateHelper.getCurrentMonth(), DateHelper.getCurrentYear());
		}
	}

	private void setupFlextimeDay(String newDate, long startTime, long endTime) {
		currentFlextimeDay = Factory.getInstance().createFlextimeDay(newDate,
				startTime, endTime, new ArrayList<WorkBreak>());
		updateDateTv();
		updateTimeTv();
	}

	@Override
	public void saveFlextimeDay() {
		save();
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_flextime_day_setup);
	}

	public FlextimeDay getFlextimeDay() {
		return currentFlextimeDay;
	}

	@Override
	protected void initElements() {
		date = (DatePicker) findViewById(R.id.day);
		date.getCalendarView().setOnDateChangeListener(
				new DateChangedListener(this));
		dateTv = (TextView) findViewById(R.id.day_tv);
		timeRangeTV = (TextView) findViewById(R.id.time_range_tv);
	}

	@Override
	protected int getMenu() {
		return R.menu.flextime_day_setup_menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.setup_time:
			FlextimeTimeSetupDialog.newInstance(this).show(
					getSupportFragmentManager(), TAG);
			break;
		case R.id.save: {
			save();
			break;
		}
		case R.id.show_breaks: {
			startNextActivitWithExtras(BreakOverviewActivity.class);
			break;
		}
		case R.id.abort: {
			onBackPressed();
			break;
		}
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public Bundle getExtras() {
		Bundle extra = new Bundle();
		extra.putString("date", currentFlextimeDay.getDate());
		return extra;
	}

	@Override
	public void onBackPressed() {
		SaveOrDiscardDialog.newInstance(this).show(getSupportFragmentManager(),
				TAG);
	}

	@Override
	public void updateTime(long startTime, long endTime) {
		setupFlextimeDay(currentFlextimeDay.getDate(), startTime, endTime);
	}

	@Override
	public void save() {
		if (((FlexplanApplication) getApplication()).getDbHelper().isDateInDB(
				currentFlextimeDay.getDate())) {
			OverwriteDialog.newInstance(this).show(getSupportFragmentManager(),
					TAG);
		} else {
			overwriteOrSave();
		}
	}

	@Override
	public void overwriteOrSave() {
		((FlexplanApplication) getApplication()).getDbHelper().delete(
				currentFlextimeDay);
		((FlexplanApplication) getApplication()).getDbHelper()
				.insertOrUpdateFlextimeDay(currentFlextimeDay);
		super.onBackPressed();
	}

	@Override
	public void discard() {
		((FlexplanApplication) getApplication()).getDbHelper().delete(
				currentFlextimeDay);
		super.onBackPressed();
	}

	@Override
	public void updateDate(int day, int month, int year) {
		String newDate = DateHelper.getDateAsString(day, month, year);
		date.getCalendarView().setDate(DateHelper.convertDateStringToLong(day, month, year));
		long startTime = DateHelper.DAY_START;
		long endTime = DateHelper.DAY_END;
		if (((FlexplanApplication) getApplication()).getDbHelper().isDateInDB(
				newDate)) {
			startTime = ((FlexplanApplication) getApplication()).getDbHelper()
					.getStartTimeOfDay(newDate);
			endTime = ((FlexplanApplication) getApplication()).getDbHelper()
					.getEndTimeOfDay(newDate);
		}
		setupFlextimeDay(newDate, startTime, endTime);
	}

	private void updateTimeTv() {
		StringBuilder sb = new StringBuilder();
		sb.append(DateHelper.getTimeAsString(currentFlextimeDay.getStartTime()))
				.append(" - ")
				.append(DateHelper.getTimeAsString(currentFlextimeDay
						.getEndTime()));
		timeRangeTV.setText(sb.toString());
	}

	private void updateDateTv() {
		dateTv.setText(currentFlextimeDay.getDate());
	}

}
