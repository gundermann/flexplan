package com.flexplan;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.util.AbstractActivity;
import com.flexplan.util.SaveOrDiscardDialog;

public class BreakOverviewActivity extends AbstractActivity implements
		BreakSetup, SaveDiscardProvider {

	private FlextimeDay currentFlextimeDay;
	private ListView breakListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadBreak();
		updateList();
	}

	private void loadBreak() {
		currentFlextimeDay = ((FlexplanApplication) getApplication())
				.getCacheDB().getCachedFlextimeDay();
		this.setTitle(getTitle() + " - " + currentFlextimeDay.getDate());
	}

	private void updateList() {
		breakListView.setAdapter(new BreakListAdapter(getApplicationContext(),
				currentFlextimeDay.getWorkBreaks()));
		breakListView.setEmptyView(findViewById(R.id.empty));
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_break_view);
	}

	@Override
	public void onBackPressed() {
		SaveOrDiscardDialog.newInstance(this).show(getSupportFragmentManager(),
				TAG);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_break:
			BreakSetupDialog.newInstance(this).show(
					getSupportFragmentManager(), TAG);
			break;
		case R.id.save:
			save();
			break;
		case R.id.discard:
			discard();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void initElements() {
		breakListView = (ListView) findViewById(R.id.breakList);
	}

	@Override
	protected int getMenu() {
		return R.menu.break_overview_menu;
	}

	@Override
	public void addBreak(long startTime, long endTime) {
		WorkBreak workbreak = Factory.getInstance().createWorkBreak(startTime,
				endTime);
		currentFlextimeDay.addBreak(workbreak);
		updateList();
	}

	@Override
	public void save() {
		((FlexplanApplication) getApplication()).getCacheDB().insertWorkBreaks(
				currentFlextimeDay);
		super.onBackPressed();
	}

	@Override
	public void discard() {
		super.onBackPressed();
	}
}
