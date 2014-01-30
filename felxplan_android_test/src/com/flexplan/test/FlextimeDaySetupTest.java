package com.flexplan.test;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.flexplan.FlextimeDaySetupActivity;
import com.flexplan.common.util.DateHelper;

public class FlextimeDaySetupTest extends
		AbstractActivityInstumentaionTest<FlextimeDaySetupActivity> {

	private FlextimeDaySetupActivity activity;
	private int dayDpId = com.flexplan.R.id.day;
	private int setupTimeBtId = com.flexplan.R.id.setup_time_button;
	private int saveFlextimeDayBtId = com.flexplan.R.id.save_flextimeday_button;
	private int dayTvId = com.flexplan.R.id.day_tv;

	public FlextimeDaySetupTest() {
		super(FlextimeDaySetupActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dexmaker.dexcache", getInstrumentation()
				.getTargetContext().getCacheDir().getPath());

		activity = getActivity();
	}

	public void testLayout() {
		DatePicker dayDp = (DatePicker) activity.findViewById(dayDpId);
		Button setupTimeBt = (Button) activity.findViewById(setupTimeBtId);
		Button saveFlextimeBt = (Button) activity
				.findViewById(saveFlextimeDayBtId);
		TextView dayTv = (TextView) activity.findViewById(dayTvId);
		assertNotNull(dayDp);
		assertNotNull(setupTimeBt);
		assertNotNull(saveFlextimeBt);
		assertNotNull(dayTv);

		assertEquals("Incorrect default day of DatePicker",
				DateHelper.getCurrentDayOfMonth(), dayDp.getDayOfMonth());
		assertEquals("Incorrect default month of DatePicker",
				DateHelper.getCurrentMonth(), dayDp.getMonth());
		assertEquals("Incorrect default year of DatePicker",
				DateHelper.getCurrentYear(), dayDp.getYear());
		assertEquals("Incorrect label of the button for timesetup",
				activity.getText(com.flexplan.R.string.setup_time),
				setupTimeBt.getText());
		assertEquals("Incorrect label of the savebutton",
				activity.getText(com.flexplan.R.string.save),
				saveFlextimeBt.getText());
		assertEquals("incorrect text on dayTV",
				DateHelper.getCurrentDateAsString(), dayTv.getText().toString());

	}
	
	public void testStartFelxtimeTimeSetup() throws Exception {
		Button viewToTouch = (Button) activity.findViewById(setupTimeBtId);
		int viewIdToCheck = com.flexplan.R.id.save_flextimeday_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save)
				.toString();
		testStartNextActivity(FlextimeDaySetupActivity.class, viewToTouch,
				viewIdToCheck, valueToVerify);
	}

	// public void testSaveFlextimeDay() throws Exception{
	// Button viewToTouch = (Button) activity.findViewById(saveFlextimeDayBtId);
	// int listViewIdToCheck = com.flexplan.R.id.flextime_overview;
	// List<FlextimeDay> valuesToVerify = new ArrayList<FlextimeDay>();
	// valuesToVerify.add(null);
	// valuesToVerify.add(FlextimeDayTestSupport.createDefaultTuesday());
	// valuesToVerify.add(null);
	// testStartNextActivityWithListView(FlextimeOverviewActivity.class,
	// viewToTouch, listViewIdToCheck, valuesToVerify);
	// }

}
