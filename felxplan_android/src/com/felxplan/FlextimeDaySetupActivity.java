package com.felxplan;

import java.util.List;

import com.felxplan.util.SystemUiHider;
import com.flexplan.common.FlextimeDayFactory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FlextimeDaySetupActivity extends Activity {
	private static final boolean AUTO_HIDE = true;

	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	private static final boolean TOGGLE_ON_CLICK = true;

	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	private SystemUiHider mSystemUiHider;

	private FlextimeDay currentFlextimeDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_flextime_day_setup);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);

		final TimePicker timeFrom = (TimePicker) findViewById(R.id.timeFrom);
		final TimePicker timeTo = (TimePicker) findViewById(R.id.timeTo);

		timeFrom.setIs24HourView(true);
		timeTo.setIs24HourView(true);

		Button addBreakButton = (Button) findViewById(R.id.add_break_button);
		addBreakButton.setOnClickListener(new AddBreakListener(getApplicationContext()));

		Button saveButton = (Button) findViewById(R.id.save_flextimeday_button);
		saveButton.setOnClickListener(new SaveFlextimeDayListener(
				getCurrentFlextimeDay(),
				((FlexplanApplication) getApplication()).getDbHelper()));

		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});

		findViewById(R.id.add_break_button).setOnTouchListener(
				mDelayHideTouchListener);
	}

	private FlextimeDay getCurrentFlextimeDay() {
		saveFlextime();
		return currentFlextimeDay;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		delayedHide(100);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}

	private void saveFlextime() {
		if (currentFlextimeDay == null
				|| currentFlextimeDay.getDate() != getDate()) {
			currentFlextimeDay = FlextimeDayFactory.createFlextimeDay(
					getDate(), getStartTime(), getEndTime(), getBreaks());
		} else {
			currentFlextimeDay.setStartTime(getStartTime());
			currentFlextimeDay.setEndTime(getEndTime());
			for (WorkBreak breakTime : getBreaks()) {
				currentFlextimeDay.addBreak(breakTime);
			}
		}

	}

	private List<WorkBreak> getBreaks() {
		// TODO Auto-generated method stub
		return null;
	}

	private long getEndTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	private long getStartTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	private long getDate() {
		// TODO Auto-generated method stub
		return 0;
	}
}
