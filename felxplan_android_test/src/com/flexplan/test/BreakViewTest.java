package com.flexplan.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.flexplan.BreakSetupActivity;
import com.flexplan.BreakViewActivity;
import com.flexplan.FlextimeDaySetupActivity;
import com.flexplan.FlextimeOverviewActivity;
import com.flexplan.FlextimeTimeSetupActivity;

public class BreakViewTest extends
		ActivityTest<BreakViewActivity> {

	private BreakViewActivity activity;
	private int breaksTvId = com.flexplan.R.id.break_overview_tv;
	private int breakLvId = com.flexplan.R.id.breakList;
	private int setupBreakBtId = com.flexplan.R.id.add_break_button;

	public BreakViewTest() {
		super(BreakViewActivity.class);
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
		assertNotNull(activity.findViewById(breaksTvId));
		assertNotNull(activity.findViewById(breakLvId));
		assertNotNull(activity.findViewById(setupBreakBtId));

		Button setupBreakBt = (Button) activity.findViewById(setupBreakBtId);
		assertEquals("Incorrect label of the button for breaksetup",
				activity.getText(com.flexplan.R.string.add_break),
				setupBreakBt.getText());
		TextView breaksTv = (TextView) activity.findViewById(breaksTvId);
		assertEquals("Incorrect label of the breakoverviewbutton",
				activity.getText(com.flexplan.R.string.break_overview),
				breaksTv.getText());
		ListView breakLv = (ListView) activity.findViewById(breakLvId);
		assertNotNull(activity.getText(com.flexplan.R.string.break_overview));
	}

	public void testStartBreakSetupActivity() throws Exception{
		Button viewToTouch = (Button) activity.findViewById(setupBreakBtId);
		int viewIdToCheck = com.flexplan.R.id.save_break_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save_break).toString();
		testStartNextActivity(BreakSetupActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
}
