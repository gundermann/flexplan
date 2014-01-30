package com.flexplan.test;

import java.util.GregorianCalendar;

import android.widget.Button;
import android.widget.DatePicker;

import com.flexplan.FlextimeDaySetupActivity;

public class FlextimeDaySetupTest extends
		AbstractActivityInstumentaionTest<FlextimeDaySetupActivity>{

	private FlextimeDaySetupActivity activity;
	private int dayDpId = com.flexplan.R.id.day;
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
		
		activity = getActivity();
	}

	public void testLayout() {
		assertNotNull(activity.findViewById(dayDpId));
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
				activity.getText(com.flexplan.R.string.save),
				saveFlextimeBt.getText());
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

	public void testStartFelxtimeTimeSetup() throws Exception {
		Button viewToTouch = (Button) activity.findViewById(setupTimeBtId);
		int viewIdToCheck = com.flexplan.R.id.save_flextimeday_button;
		String valueToVerify = activity.getText(com.flexplan.R.string.save).toString();
		testStartNextActivity(FlextimeDaySetupActivity.class, viewToTouch, viewIdToCheck, valueToVerify);
	}
	
//	public void testSaveFlextimeDay() throws Exception{
//		Button viewToTouch = (Button) activity.findViewById(saveFlextimeDayBtId);
//		int listViewIdToCheck = com.flexplan.R.id.flextime_overview;
//		List<FlextimeDay> valuesToVerify = new ArrayList<FlextimeDay>();
//		valuesToVerify.add(null);
//		valuesToVerify.add(FlextimeDayTestSupport.createDefaultTuesday());
//		valuesToVerify.add(null);
//		testStartNextActivityWithListView(FlextimeOverviewActivity.class, viewToTouch, listViewIdToCheck,  valuesToVerify);
//	}

	

}
