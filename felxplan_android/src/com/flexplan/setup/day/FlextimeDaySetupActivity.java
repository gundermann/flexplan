package com.flexplan.setup.day;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.flexplan.AbstractFlextimeActivity;
import com.flexplan.BreakOverviewActivity;
import com.flexplan.FlexplanApplication;
import com.flexplan.ListenerFactory;
import com.flexplan.R;
import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.util.DateHelper;
import com.flexplan.persistence.StartTimeSetupListener;
import com.flexplan.setup.EndTimeSetupListener;
import com.flexplan.setup.SaveDiscardProvider;
import com.flexplan.util.DateChangedListener;
import com.flexplan.util.OverwriteProvider;
import com.flexplan.util.SaveOrDiscardDialog;
import com.flexplan.util.SimpleDialog;

public class FlextimeDaySetupActivity extends AbstractFlextimeActivity
		implements FlextimeDaySetup, SaveDiscardProvider, OverwriteProvider{

	private static final String TAG = null;
	private DatePicker date;
	private TextView dateTv;
	private Button timeFromBT;
	private Button timeToBT;

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
		updateTimeBTs();
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
		timeFromBT = (Button) findViewById(R.id.start_time);
		timeFromBT.setOnClickListener(new StartTimeSetupListener(this, this));
		timeToBT = (Button) findViewById(R.id.end_time);
		timeToBT.setOnClickListener(new EndTimeSetupListener(this, this));
	}

	@Override
	protected int getMenu() {
		return R.menu.flextime_day_setup_menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
	public void save() {
		if (((FlexplanApplication) getApplication()).getFlextimeDB()
				.isDateInDB(currentFlextimeDay.getDate())) {
			SimpleDialog.newInstance(
					ListenerFactory.createOverrideListener(this),
					getString(R.string.override_day)).show(
					getSupportFragmentManager(), TAG);
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

	private void updateTimeBTs() {
		timeFromBT.setText(DateHelper.getTimeAsString(currentFlextimeDay
				.getStartTime()));
		timeToBT.setText(DateHelper.getTimeAsString(currentFlextimeDay
				.getEndTime()));

	}

	private void updateDateTv() {
		dateTv.setText(currentFlextimeDay.getDate());
	}

	@Override
	public String getSaveDiscardMessage() {
		return getString(R.string.save_or_discard_day);
	}

	@Override
	public long getStartTime() {
		return getCurrentFlextimeDay().getStartTime();
	}

	@Override
	public long getEndTime() {
		return getCurrentFlextimeDay().getEndTime();
	}

	@Override
	public void setTime(long startTime, long endTime) {
		setupFlextimeDay(currentFlextimeDay.getDate(), startTime, endTime);		
	}
}
