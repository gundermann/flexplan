package com.flexplan;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.util.DateHelper;
import com.flexplan.util.DateChangedListener;
import com.flexplan.util.OverwriteDialog;
import com.flexplan.util.OverwriteProvider;
import com.flexplan.util.SaveOrDiscardDialog;

public class FlextimeDaySetupActivity extends AbstractFlextimeActivity
		implements FlextimeDaySetup, SaveDiscardProvider, OverwriteProvider {

	private static final String TAG = null;
	private DatePicker date;
	private TextView dateTv;
	private TextView timeRangeTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!existsCacheData()) {
			updateDate(DateHelper.getCurrentDayOfMonth(),
					DateHelper.getCurrentMonth(), DateHelper.getCurrentYear());
		}
	}

	private boolean existsCacheData() {
		if (((FlexplanApplication) getApplication()).existsCacheData()) {
			String dateString = ((FlexplanApplication) getApplication())
					.getCacheDB().getCachedFlextimeDay().getDate();
			date.getCalendarView().setDate(
					DateHelper.convertDateStringToLong(dateString));
			updateDate(Integer.valueOf(dateString.substring(0, 2)),
					Integer.valueOf(dateString.substring(3, 5)),
					Integer.valueOf(dateString.substring(6)));
			return true;
		}
		return false;
	}

	private void setupFlextimeDay(String newDate, long startTime, long endTime) {
		currentFlextimeDay = Factory.getInstance().createFlextimeDay(newDate,
				startTime, endTime,
				getCacheDbHelper().getWorkBreaksForFlextimeDay(newDate));
		updateDateTv();
		updateTimeTv();
		updateCache();
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
			startNextActivity(BreakOverviewActivity.class);
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
		if (((FlexplanApplication) getApplication()).getFlextimeDB()
				.isDateInDB(currentFlextimeDay.getDate())) {
			OverwriteDialog.newInstance(this).show(getSupportFragmentManager(),
					TAG);
		} else {
			overwriteOrSave();
		}
	}

	@Override
	public void overwriteOrSave() {
		((FlexplanApplication) getApplication()).getFlextimeDB().delete(
				currentFlextimeDay);
		((FlexplanApplication) getApplication()).getFlextimeDB()
				.insertOrUpdateFlextimeDay(currentFlextimeDay);
		super.onBackPressed();
	}

	@Override
	public void discard() {
		((FlexplanApplication) getApplication()).getCacheDB().cleanup();
		super.onBackPressed();
	}

	@Override
	public void updateDate(int day, int month, int year) {
		String newDate = DateHelper.getDateAsString(day, month, year);
		long startTime = DateHelper.DAY_START;
		long endTime = DateHelper.DAY_END;
		if (((FlexplanApplication) getApplication()).isDateCached(newDate)) {
			startTime = ((FlexplanApplication) getApplication()).getCacheDB()
					.getStartTimeOfDay(newDate);
			endTime = ((FlexplanApplication) getApplication()).getCacheDB()
					.getEndTimeOfDay(newDate);
		}

		else if (((FlexplanApplication) getApplication()).getFlextimeDB()
				.isDateInDB(newDate)) {
			startTime = ((FlexplanApplication) getApplication())
					.getFlextimeDB().getStartTimeOfDay(newDate);
			endTime = ((FlexplanApplication) getApplication()).getFlextimeDB()
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

	@Override
	public String getOverwirteMessage() {
		return getString(R.string.override_day);
	}

	@Override
	public String getSaveDiscardMessage() {
		return getString(R.string.save_or_discard_day);
	}

}
