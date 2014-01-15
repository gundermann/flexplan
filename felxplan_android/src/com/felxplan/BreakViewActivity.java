package com.felxplan;

import android.os.Bundle;
import android.widget.ListView;

import com.felxplan.util.SystemUiHider;
import com.flexplan.common.business.FlextimeDay;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class BreakViewActivity extends AbstractActivity {

	private FlextimeDay currentFlextimeDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ListView breakListView = (ListView) findViewById(R.id.breakList);
		breakListView.setAdapter(new BreakListAdapter(getApplicationContext(),
				currentFlextimeDay.getWorkBreaks()));

	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_break_view);		
	}

}
