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

public class BreakSetupTest extends
		ActivityTest<FlextimeTimeSetupActivity> {

	private FlextimeTimeSetupActivity activity;
	private int timeFromTvId = com.flexplan.R.id.timeFrom_tv;
	private int timeFromTpId = com.flexplan.R.id.timeFrom;
	private int timeToTvId = com.flexplan.R.id.timeTo_tv;
	private int timeToTpId = com.flexplan.R.id.timeTo;
	private int abortBtId = com.flexplan.R.id.abort;
	private int saveBreakBtId = com.flexplan.R.id.save_break_button;

	public BreakSetupTest() {
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
		assertNotNull(activity.findViewById(abortBtId));
		assertNotNull(activity.findViewById(saveBreakBtId));

		Button setupBreakBt = (Button) activity.findViewById(saveBreakBtId);
		assertEquals("Incorrect label of the button for daysetup",
				activity.getText(com.flexplan.R.string.save_break),
				setupBreakBt.getText());
		Button abortBt = (Button) activity
				.findViewById(abortBtId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.abort),
				abortBt.getText());
		TextView timeFromTv = (TextView) activity.findViewById(timeFromTvId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.date),
				timeFromTv.getText());
		TextView timeToTv = (TextView) activity.findViewById(timeToTvId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.date),
				timeToTv.getText());
	}

	public void testStartBreakSetupActivityBySaving() throws Exception {
		Button viewToTouch = (Button) activity.findViewById(saveBreakBtId);
		int viewIdToCheck = com.flexplan.R.id.save_flextimeday_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save_flextimeday).toString();
		testStartNextActivity(BreakViewActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
	public void testStartBreakViewActivityByAbort() throws Exception{
		Button viewToTouch = (Button) activity.findViewById(abortBtId);
		int viewIdToCheck = com.flexplan.R.id.save_break_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save_break).toString();
		testStartNextActivity(BreakViewActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
}
