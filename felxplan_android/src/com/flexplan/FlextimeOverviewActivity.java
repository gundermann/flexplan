package com.flexplan;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.flexplan.setup.day.FlextimeDaySetupActivity;
import com.flexplan.util.WeekChangeListener;

public class FlextimeOverviewActivity extends AbstractFlextimeActivity
		implements OnPageChangeListener {

	private int actualWeek;

	private ViewPager viewPager;

	private ImageButton prevWeekBt;

	private ImageButton nextWeekBt;

	private WeekAdapter weekAdapter;

	private boolean canSwitchYear = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.title_activity_flextime_overview);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getCacheDbHelper().cleanup();
		viewPager.setCurrentItem(actualWeek - 1);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_flextime_overview);
	}

	@Override
	protected void initElements() {
		setupWeek();
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setOnPageChangeListener(this);
		weekAdapter = new WeekAdapter(getSupportFragmentManager());
		viewPager.setAdapter(weekAdapter);
		viewPager.setCurrentItem(actualWeek - 1);

		prevWeekBt = (ImageButton) findViewById(R.id.prev_week);
		nextWeekBt = (ImageButton) findViewById(R.id.next_week);
		prevWeekBt.setOnClickListener(new WeekChangeListener(this, -1));
		nextWeekBt.setOnClickListener(new WeekChangeListener(this, +1));
	}

	public void switchToWeek(int change) {
		viewPager.setCurrentItem(viewPager.getCurrentItem() + change, true);
		if (viewPager.getCurrentItem() == 0 && change == -1) {
			weekAdapter.initPrevYear();
			canSwitchYear = false;
			viewPager.setCurrentItem(53, false);
			viewPager.setCurrentItem(52, true);
		}
	}

	private void setupWeek() {
		GregorianCalendar cal = new GregorianCalendar();
		actualWeek  = cal.get(GregorianCalendar.WEEK_OF_YEAR);
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
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int position, float arg1, int arg2) {
		if (position == 0) {
			canSwitchYear = false;
			weekAdapter.initPrevYear();
			viewPager.setCurrentItem(53, false);
		}
		if (position == 53 && canSwitchYear) {
			weekAdapter.initNextYear();
			viewPager.setCurrentItem(1, false);
		} else {
			viewPager.getAdapter().getItemPosition(position);
			canSwitchYear = true;
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}
