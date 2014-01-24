package com.flexplan.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.view.View;

public abstract class ActivityTest<T extends Activity> extends
		ActivityUnitTestCase<T> {
	
	protected View activity;

	public ActivityTest(Class<T> activityClass) {
		super(activityClass);
	}


	public void testStartNextActivity(Class<? extends Activity> nextActivityClass, View viewToTouch, int viewIdToCheck, String valueToVerify) throws Exception {
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				nextActivityClass.getName(), null, false);

		TouchUtils.clickView(this, viewToTouch);
		Activity startedActivity = (Activity) monitor
				.waitForActivityWithTimeout(5000);
		assertNotNull(startedActivity);

		View viewToCheck = startedActivity.findViewById(viewIdToCheck);
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				viewToCheck);
		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, viewToTouch);
	}
}
