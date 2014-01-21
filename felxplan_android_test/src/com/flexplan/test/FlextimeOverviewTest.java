package com.flexplan.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ListView;

import com.flexplan.FlextimeDaySetupActivity;
import com.flexplan.FlextimeOverviewActivity;

public class FlextimeOverviewTest extends
		ActivityUnitTestCase<FlextimeOverviewActivity> {

	private FlextimeOverviewActivity activity;
	private int nextWeekBtId = com.flexplan.R.id.next_week;
	private int prevWeekBtId = com.flexplan.R.id.prev_week;
	private int setupFlextimeDayBtId = com.flexplan.R.id.setup_flextime_day;
	private int weekTvId = com.flexplan.R.id.week;
	private int overviewLvId = com.flexplan.R.id.flextime_overview;

	public FlextimeOverviewTest() {
		super(FlextimeOverviewActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setApplication(FlexplanAppTestHelper.createMockApp());
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				FlextimeOverviewActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	public void testLayout() {
	    assertNotNull(activity.findViewById(nextWeekBtId));
	    assertNotNull(activity.findViewById(prevWeekBtId));
	    assertNotNull(activity.findViewById(setupFlextimeDayBtId));
	    assertNotNull(activity.findViewById(weekTvId));
	    assertNotNull(activity.findViewById(overviewLvId));
	    
	    ListView overviewLv = (ListView) activity.findViewById(overviewLvId);
	    
	    assertEquals("Incorrect count of days in week",  overviewLv.getAdapter().getCount(), 7);
	    
	    Button addFlextimeBt= (Button) activity.findViewById(setupFlextimeDayBtId);
	    assertEquals("Incorrect label of the button", activity.getText(com.flexplan.R.string.add_flextime), addFlextimeBt.getText());
	  }

	public void testStartSecondActivity() throws Exception {
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				FlextimeDaySetupActivity.class.getName(), null, false);

		Button view = (Button) activity.findViewById(setupFlextimeDayBtId);

		TouchUtils.clickView(this, view);

		FlextimeDaySetupActivity startedActivity = (FlextimeDaySetupActivity) monitor
				.waitForActivityWithTimeout(5000);
		assertNotNull(startedActivity);

		Button saveBt = (Button) startedActivity
				.findViewById(com.flexplan.R.id.save_flextimeday_button);

		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				saveBt);
		assertEquals("Text incorrect", "Started", saveBt.getText().toString());

		this.sendKeys(KeyEvent.KEYCODE_BACK);

		TouchUtils.clickView(this, view);
	}

}
