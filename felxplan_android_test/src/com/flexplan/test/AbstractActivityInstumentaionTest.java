package com.flexplan.test;

import java.util.List;

import com.flexplan.FlextimeOverviewActivity;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public abstract class AbstractActivityInstumentaionTest<T extends Activity>
		extends ActivityInstrumentationTestCase2<T> {

	protected View activity;

	public AbstractActivityInstumentaionTest(Class<T> activityClass) {
		super(activityClass);
	}

	public void testStartNextActivity(
			Class<? extends Activity> nextActivityClass, View viewToTouch,
			int viewIdToCheck, String valueToVerify) throws Exception {
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				nextActivityClass.getName(), null, false);

		TouchUtils.clickView(this, viewToTouch);
		Activity startedActivity = monitor.waitForActivityWithTimeout(5000);
		assertNotNull(startedActivity);

		TextView viewToCheck = (TextView) startedActivity
				.findViewById(viewIdToCheck);
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				viewToCheck);
		assertEquals("incorrect view", valueToVerify, viewToCheck.getText()
				.toString());

		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, viewToTouch);
	}

	protected void testStartNextActivityWithListView(
			Class<FlextimeOverviewActivity> nextActivityClass,
			Button viewToTouch, int listViewIdToCheck, List<?> valuesToVerify) {
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				nextActivityClass.getName(), null, false);

		TouchUtils.clickView(this, viewToTouch);
		Activity startedActivity = monitor.waitForActivityWithTimeout(5000);
		assertNotNull(startedActivity);

		ListView viewToCheck = (ListView) startedActivity
				.findViewById(listViewIdToCheck);
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				viewToCheck);
		assertEquals("incorrect type of adapter", valuesToVerify.get(0)
				.getClass(), viewToCheck.getAdapter().getClass());
		checkListView(viewToCheck, valuesToVerify);

		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, viewToTouch);
	}

	private void checkListView(ListView viewToCheck, List<?> valuesToVerify) {
		for (int i = 0; i < valuesToVerify.size(); i++) {
			if (valuesToVerify.get(i) != null) {
				assertEquals("incorrect object in list", valuesToVerify.get(i),
						viewToCheck.getAdapter().getItem(0));

			} else {
				assertNotNull("view in list is null", viewToCheck.getAdapter()
						.getView(i, viewToCheck, viewToCheck));
			}
		}
	}
}
