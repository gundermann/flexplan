package com.flexplan.test;

import java.util.GregorianCalendar;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.flexplan.FlextimeDaySetupActivity;
import com.flexplan.FlextimeOverviewActivity;
import com.flexplan.FlextimeTimeSetupActivity;

public class FlextimeDaySetupTest extends
		ActivityUnitTestCase<FlextimeDaySetupActivity> {

	private FlextimeDaySetupActivity activity;
	private int dayDpId = com.flexplan.R.id.day;
	private int dateTvId = com.flexplan.R.id.date_textview;
	private int setupTimeBtId = com.flexplan.R.id.setup_time_button;
	private int saveFlextimeDayBtId = com.flexplan.R.id.save_flextimeday_button;
	private GregorianCalendar cal = new GregorianCalendar();

	public FlextimeDaySetupTest() {
		super(FlextimeDaySetupActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dexmaker.dexcache", getInstrumentation()
				.getTargetContext().getCacheDir().getPath());
		setApplication(FlexplanAppTestHelper.createMockApp());
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				FlextimeDaySetupActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	public void testLayout() {
		assertNotNull(activity.findViewById(dayDpId));
		assertNotNull(activity.findViewById(dateTvId));
		assertNotNull(activity.findViewById(setupTimeBtId));
		assertNotNull(activity.findViewById(saveFlextimeDayBtId));

		DatePicker dayDp = (DatePicker) activity.findViewById(dayDpId);
		assertEquals("Incorrect default day of DatePicker",
				dayDp.getDayOfMonth(), getCurrentDayOfMonth());
		assertEquals("Incorrect default month of DatePicker", dayDp.getMonth(),
				getCurrentMonth());
		assertEquals("Incorrect default year of DatePicker", dayDp.getYear(),
				getCurrentYear());

		Button setupTimeBt = (Button) activity.findViewById(setupTimeBtId);
		assertEquals("Incorrect label of the button for timesetup",
				activity.getText(com.flexplan.R.string.setup_time),
				setupTimeBt.getText());

		Button saveFlextimeBt = (Button) activity
				.findViewById(saveFlextimeDayBtId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.save_flextimeday),
				saveFlextimeBt.getText());

		TextView dateTv = (TextView) activity.findViewById(dateTvId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.date), dateTv.getText());
	}

	private int getCurrentYear() {
		return cal.get(GregorianCalendar.YEAR);
	}

	private int getCurrentMonth() {
		return cal.get(GregorianCalendar.MONTH);
	}

	private int getCurrentDayOfMonth() {
		return cal.get(GregorianCalendar.DAY_OF_MONTH);
	}

	public void testStartSecondActivity() throws Exception {
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				FlextimeTimeSetupActivity.class.getName(), null, false);
		Button view = (Button) activity.findViewById(setupTimeBtId);

		TouchUtils.clickView(this, view);
		FlextimeTimeSetupActivity startedActivity = (FlextimeTimeSetupActivity) monitor
				.waitForActivityWithTimeout(5000);
		assertNotNull(startedActivity);

		Button saveBt = (Button) startedActivity
				.findViewById(com.flexplan.R.id.save_flextimeday_button);
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				saveBt);
		assertEquals("Text incorrect",
				activity.getText(com.flexplan.R.string.save_flextimeday),
				saveBt.getText().toString());

		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, view);
	}

}
