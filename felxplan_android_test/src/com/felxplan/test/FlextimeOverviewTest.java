package com.felxplan.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import com.felxplan.FlextimeOverviewActivity;

public class FlextimeOverviewTest extends ActivityUnitTestCase<FlextimeOverviewActivity>{

	private FlextimeOverviewActivity activity;
	private int nextWeekBtId;
	private int prevWeekBtId;
	private int addTimeBId;

	public FlextimeOverviewTest() {
		super(FlextimeOverviewActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				FlextimeOverviewActivity.class);
		activity = getActivity();
		activity.setDbHelper(FlextimeDBTestHelper.createFlextimeDB());
		startActivity(intent, null, null);
//		activity.setMatchApp(MatchAppTestHelper.createUpdatedMatchApp());
	}
	
	public void testLayout() {
	    nextWeekBtId = com.felxplan.R.id.next_week;
	    prevWeekBtId = com.felxplan.R.id.prev_week;
	    addTimeBId = com.felxplan.R.id.add_flextime_button;
	    
	    assertNotNull(activity.findViewById(nextWeekBtId));
	    assertNotNull(activity.findViewById(prevWeekBtId));
	    assertNotNull(activity.findViewById(addTimeBId));
	    
	    Button nextWeekBt = (Button) activity.findViewById(nextWeekBtId);
	    
	    Button prevWeekBt = (Button) activity.findViewById(prevWeekBtId);
	    
	    Button addFlextimeBt= (Button) activity.findViewById(addTimeBId);
	    assertEquals("Incorrect label of the button", activity.getText(com.felxplan.R.string.add_flextime), addFlextimeBt.getText());
	  }

}
