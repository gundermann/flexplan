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
import com.flexplan.util.SaveOrDiscardDialog;

public class FlextimeDaySetupActivity extends AbstractActivityExtraProvider
		implements FlextimeDaySetup, DateFieldProvider, SaveDiscardProvider {

	private static final String TAG = null;
	private FlextimeDay currentFlextimeDay;
	private DatePicker date;
	private TextView dateTv;
	private TextView timeRangeTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupFlextimeDay();
	}

	private void setupFlextimeDay() {
		if (currentFlextimeDay == null
				|| currentFlextimeDay.getDate() != getDate()) {
			currentFlextimeDay = Factory.getInstance().createFlextimeDay(
					getDate(), DateHelper.DAY_START, DateHelper.DAY_END,
					new ArrayList<WorkBreak>());
		}
	}

	@Override
	public void saveFlextimeDay() {
		currentFlextimeDay.setDate(getDate());
	}

	private String getDate() {
		return DateHelper.getLongAsString(date.getCalendarView().getDate());
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
		updateDateField(0, 0, 0);
	}

	@Override
	public void updateDateField(int dayOfMonth, int month, int year) {
		dateTv.setText(getDate());
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
		extra.putString("date", getDate());
		return extra;
	}

	@Override
	public void onBackPressed() {
		SaveOrDiscardDialog.newInstance(this).show(getSupportFragmentManager(),
				TAG);
	}

	@Override
	public void updateTimeFields() {
		StringBuilder sb = new StringBuilder();
		sb.append(DateHelper.getTimeAsString(currentFlextimeDay.getStartTime()))
				.append(" - ")
				.append(DateHelper.getTimeAsString(currentFlextimeDay
						.getEndTime()));
		timeRangeTV.setText(sb.toString());
	}

	@Override
	public void updateTime(long startTime, long endTime) {
		currentFlextimeDay.setStartTime(startTime);
		currentFlextimeDay.setEndTime(endTime);
		updateTimeFields();

	}

	@Override
	public void save() {
		saveFlextimeDay();
		((FlexplanApplication) getApplication()).getDbHelper()
				.insertOrUpdateFlextimeDay(currentFlextimeDay);
		super.onBackPressed();
	}

	@Override
	public void discard() {
		((FlexplanApplication) getApplication()).getDbHelper().delete(
				currentFlextimeDay);
	}

}
