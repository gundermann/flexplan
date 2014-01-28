package com.flexplan;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.flexplan.common.FlextimeDayFactory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.util.AbstractActivity;

public class BreakViewActivity extends AbstractActivity {

	private FlextimeDay currentFlextimeDay;
	private Button addBreakBt;
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
	public List<View> getViewsForDelayedHide() {
		List<View> views = new ArrayList<View>();
		views.add(addBreakBt);
		return views;
	}

	@Override
	protected void initElements() {
		addBreakBt = (Button) findViewById(R.id.add_break_button);
		breakListView = (ListView) findViewById(R.id.breakList);
		breakListView.setAdapter(new BreakListAdapter(getApplicationContext(),
				currentFlextimeDay.getWorkBreaks()));
	}

	@Override
	protected void setup() {
		long date = getIntent().getExtras().getLong("date");
		long startTime = getIntent().getExtras().getLong("startTime");
		long endTime = getIntent().getExtras().getLong("endTime");
		currentFlextimeDay = FlextimeDayFactory.createFlextimeDay(date,
				startTime, endTime, new ArrayList<WorkBreak>());
	}

}
