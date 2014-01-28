package com.flexplan.test;

import android.widget.Button;
import android.widget.TextView;

import com.flexplan.BreakSetupActivity;
import com.flexplan.BreakViewActivity;
import com.flexplan.FlextimeTimeSetupActivity;

public class BreakSetupTest extends
		AbstractActivityInstumentaionTest<BreakSetupActivity> {

	private BreakSetupActivity activity;
	private int timeFromTvId = com.flexplan.R.id.timeFrom_tv;
	private int timeFromTpId = com.flexplan.R.id.timeFrom;
	private int timeToTvId = com.flexplan.R.id.timeTo_tv;
	private int timeToTpId = com.flexplan.R.id.timeTo;
	private int abortBtId = com.flexplan.R.id.abort;
	private int saveBreakBtId = com.flexplan.R.id.save_break_button;

	public BreakSetupTest() {
		super(BreakSetupActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dexmaker.dexcache", getInstrumentation()
				.getTargetContext().getCacheDir().getPath());
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
				activity.getText(com.flexplan.R.string.save),
				setupBreakBt.getText());
		Button abortBt = (Button) activity
				.findViewById(abortBtId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.abort),
				abortBt.getText());
		TextView timeFromTv = (TextView) activity.findViewById(timeFromTvId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.setup_date),
				timeFromTv.getText());
		TextView timeToTv = (TextView) activity.findViewById(timeToTvId);
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.setup_date),
				timeToTv.getText());
	}

	public void testStartBreakSetupActivityBySaving() throws Exception {
		Button viewToTouch = (Button) activity.findViewById(saveBreakBtId);
		int viewIdToCheck = com.flexplan.R.id.save_flextimeday_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save).toString();
		testStartNextActivity(BreakViewActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
	public void testStartBreakViewActivityByAbort() throws Exception{
		Button viewToTouch = (Button) activity.findViewById(abortBtId);
		int viewIdToCheck = com.flexplan.R.id.save_break_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save).toString();
		testStartNextActivity(BreakViewActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
}
