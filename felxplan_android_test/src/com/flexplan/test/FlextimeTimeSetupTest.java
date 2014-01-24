package com.flexplan.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.flexplan.BreakViewActivity;
import com.flexplan.FlextimeDaySetupActivity;
import com.flexplan.FlextimeOverviewActivity;
import com.flexplan.FlextimeTimeSetupActivity;

public class FlextimeTimeSetupTest extends
		ActivityTest<FlextimeTimeSetupActivity> {

	private FlextimeTimeSetupActivity activity;
	private int setupDayBtId = com.flexplan.R.id.setup_flextime_day;
	private int timeFromTvId = com.flexplan.R.id.timeFrom_tv;
	private int timeFromTpId = com.flexplan.R.id.timeFrom;
	private int timeToTvId = com.flexplan.R.id.timeTo_tv;
	private int timeToTpId = com.flexplan.R.id.timeTo;
	private int showBreaksBtId = com.flexplan.R.id.break_view_button;
	private int saveFlextimeDayBtId = com.flexplan.R.id.save_flextimeday_button;

	public FlextimeTimeSetupTest() {
		super(FlextimeTimeSetupActivity.class);
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
		assertNotNull(activity.findViewById(timeFromTpId));
		assertNotNull(activity.findViewById(timeToTpId));
		assertNotNull(activity.findViewById(timeFromTvId));
		assertNotNull(activity.findViewById(timeToTvId));
		assertNotNull(activity.findViewById(showBreaksBtId));
		assertNotNull(activity.findViewById(setupDayBtId));
		assertNotNull(activity.findViewById(saveFlextimeDayBtId));

		Button setupDayBt = (Button) activity.findViewById(setupDayBtId);
		assertEquals("Incorrect label of the button for daysetup",
				activity.getText(com.flexplan.R.string.setup_day),
				setupDayBt.getText());
		Button saveFlextimeBt = (Button) activity
				.findViewById(saveFlextimeDayBtId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.save_flextimeday),
				saveFlextimeBt.getText());
		TextView timeFromTv = (TextView) activity.findViewById(timeFromTvId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.date),
				timeFromTv.getText());
		TextView timeToTv = (TextView) activity.findViewById(timeToTvId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.date),
				timeToTv.getText());
	}

	public void testStartDaySetupActivity() throws Exception {
		Button viewToTouch = (Button) activity.findViewById(setupDayBtId);
		int viewIdToCheck = com.flexplan.R.id.save_flextimeday_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save_flextimeday).toString();
		testStartNextActivity(FlextimeDaySetupActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
	public void testStartBreakViewActivity() throws Exception{
		Button viewToTouch = (Button) activity.findViewById(showBreaksBtId);
		int viewIdToCheck = com.flexplan.R.id.add_break_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.add_break).toString();
		testStartNextActivity(BreakViewActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
	public void testStartOverviewActivity() throws Exception{
		Button viewToTouch = (Button) activity.findViewById(saveFlextimeDayBtId);
		int viewIdToCheck = com.flexplan.R.id.setup_flextime_day;
		String valueToVerify = activity.getText(com.flexplan.R.string.add_flextime).toString();
		testStartNextActivity(FlextimeOverviewActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
}
