package com.flexplan.test;

import org.w3c.dom.Text;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.flexplan.FlextimeOverviewActivity;

public class FlextimeOverviewTest extends ActivityUnitTestCase<FlextimeOverviewActivity>{

	private FlextimeOverviewActivity activity;
	private int nextWeekBtId;
	private int prevWeekBtId;
	private int addTimeBtId;
	private int weekTvId;
	private int overviewLvId;

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
	    nextWeekBtId = com.flexplan.R.id.next_week;
	    prevWeekBtId = com.flexplan.R.id.prev_week;
	    addTimeBtId = com.flexplan.R.id.add_flextime_button;
	    weekTvId = com.flexplan.R.id.week;
	    overviewLvId = com.flexplan.R.id.flextime_overview;
	    
	    assertNotNull(activity.findViewById(nextWeekBtId));
	    assertNotNull(activity.findViewById(prevWeekBtId));
	    assertNotNull(activity.findViewById(addTimeBtId));
	    assertNotNull(activity.findViewById(weekTvId));
	    
	    ImageButton nextWeekBt = (ImageButton) activity.findViewById(nextWeekBtId);
	    ImageButton prevWeekBt = (ImageButton) activity.findViewById(prevWeekBtId);
	    TextView weekTv = (TextView) activity.findViewById(weekTvId);
	    ListView overviewLv = (ListView) activity.findViewById(overviewLvId);
	    
	    assertEquals("Incorrect count of days in week",  overviewLv.getAdapter().getCount(), 7);
	    
	    Button addFlextimeBt= (Button) activity.findViewById(addTimeBtId);
	    assertEquals("Incorrect label of the button", activity.getText(com.flexplan.R.string.add_flextime), addFlextimeBt.getText());
	  }

}
