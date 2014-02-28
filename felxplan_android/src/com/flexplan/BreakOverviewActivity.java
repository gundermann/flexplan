package com.flexplan;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;
import com.flexplan.setup.ChangeProvider;
import com.flexplan.setup.DeleteDialog;
import com.flexplan.setup.DeleteListener;
import com.flexplan.setup.DeleteProvider;
import com.flexplan.setup.OnChangeClickListener;
import com.flexplan.setup.OnDeleteLongClickListener;
import com.flexplan.setup.SaveDiscardProvider;
import com.flexplan.setup.breaks.BreakSetup;
import com.flexplan.setup.breaks.BreakSetupDialog;
import com.flexplan.util.OverwriteDialog;
import com.flexplan.util.OverwriteProvider;
import com.flexplan.util.SaveOrDiscardDialog;

public class BreakOverviewActivity extends AbstractFlextimeActivity implements
		BreakSetup, SaveDiscardProvider, OverwriteProvider,
		DeleteProvider<WorkBreak>, ChangeProvider<WorkBreak> {

	private ListView breakListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadBreak();
		updateLists();
	}

	private void loadBreak() {
		currentFlextimeDay = ((FlexplanApplication) getApplication())
				.getCacheDB().getCachedFlextimeDay();
		this.setTitle(getTitle() + " - " + currentFlextimeDay.getDate());
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
			initSettings(null);
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
	public void save() {
		if (isChangeOfBreaks())
			OverwriteDialog.newInstance(this).show(getSupportFragmentManager(),
					TAG);
		else {
			overwriteOrSave();
		}
	}

	private boolean isChangeOfBreaks() {
		FlextimeDay cachedFlextimeDay = getCacheDbHelper()
				.getCachedFlextimeDay();
		return cachedFlextimeDay.getWorkBreaks().equals(
				currentFlextimeDay.getWorkBreaks());
	}

	@Override
	public void discard() {
		super.onBackPressed();
	}

	@Override
	public void overwriteOrSave() {
		getCacheDbHelper().updateWorkBreaks(currentFlextimeDay);
		super.onBackPressed();
	}

	@Override
	public String getOverwirteMessage() {
		return getString(R.string.override_cached_breaks);
	}

	@Override
	public String getSaveDiscardMessage() {
		return getString(R.string.save_or_discard_breaks);
	}

	private void updateLists() {
		breakListView.setAdapter(new BreakListAdapter(getApplicationContext(),
				currentFlextimeDay.getWorkBreaks()));
		breakListView
				.setOnItemClickListener(new OnChangeClickListener<WorkBreak>(
						this));
		breakListView
				.setOnItemLongClickListener(new OnDeleteLongClickListener<WorkBreak>(
						this));
		breakListView.setEmptyView(findViewById(R.id.empty));
	}

	@Override
	public void refreshBreakTime(WorkBreak workbreak, long startTime,
			long endTime) {
		if (workbreak == null) {
			workbreak = Factory.getInstance().createWorkBreak(
					DateHelper.DAY_START, DateHelper.DAY_END);
			currentFlextimeDay.addBreak(workbreak);
		}
		workbreak.setStartTime(startTime);
		workbreak.setEndTime(endTime);
		updateLists();
	}

	@Override
	public void initSettings(WorkBreak workbreak) {
		BreakSetupDialog.newInstance(this, workbreak).show(
				getSupportFragmentManager(), TAG);
	}

	@Override
	public void delete(WorkBreak workbreak) {
		currentFlextimeDay.deleteBreak(workbreak);
		updateLists();
	}

	@Override
	public void initDelete(WorkBreak workbreak) {
		DeleteDialog
				.newInstance(new DeleteListener<WorkBreak>(this, workbreak))
				.show(getSupportFragmentManager(), TAG);
	}

	@Override
	public void initChange(WorkBreak workbreak) {
		initSettings(workbreak);
	}

}
