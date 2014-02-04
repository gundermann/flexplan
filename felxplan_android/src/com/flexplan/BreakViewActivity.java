package com.flexplan;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;
import com.flexplan.util.AbstractActivityWithExtraInput;

public class BreakViewActivity extends AbstractActivityWithExtraInput implements BreakSetup {

	private FlextimeDay currentFlextimeDay;
	private ListView breakListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_break_view);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_break:
			BreakSetupDialog.newInstance(this).show(getSupportFragmentManager(), TAG);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void initElements() {
		breakListView = (ListView) findViewById(R.id.breakList);
		breakListView.setAdapter(new BreakListAdapter(getApplicationContext(),
				currentFlextimeDay.getWorkBreaks()));
		this.setTitle(getTitle() + " - "+ DateHelper.getDateAsString(currentFlextimeDay.getDate()));
	}

	@Override
	protected void setupExtras() {
		long date = getIntent().getExtras().getLong("date");
		long startTime = getIntent().getExtras().getLong("startTime");
		long endTime = getIntent().getExtras().getLong("endTime");
		currentFlextimeDay = Factory.getInstance().createFlextimeDay(date,
				startTime, endTime, new ArrayList<WorkBreak>());
	}

	@Override
	protected int getMenu() {
		return R.menu.break_overview_menu;
	}

	@Override
	public void addBreak(long startTime, long endTime) {
		WorkBreak workbreak = Factory.getInstance().createWorkBreak(startTime, endTime);
		currentFlextimeDay.addBreak(workbreak);
	}
	
	

}
