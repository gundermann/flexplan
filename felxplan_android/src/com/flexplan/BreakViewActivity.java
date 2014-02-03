package com.flexplan;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.flexplan.common.FlextimeDayFactory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.DateHelper;
import com.flexplan.util.AbstractActivityWithExtraInput;
import com.flexplan.util.ExtraProvider;
import com.flexplan.util.NextActivityClickListenerWithExtraInput;

public class BreakViewActivity extends AbstractActivityWithExtraInput implements ExtraProvider {

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
	protected void initElements() {
		addBreakBt = (Button) findViewById(R.id.add_break_button);
		addBreakBt.setOnClickListener(new NextActivityClickListenerWithExtraInput(this,
				BreakSetupActivity.class));
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
		currentFlextimeDay = FlextimeDayFactory.createFlextimeDay(date,
				startTime, endTime, new ArrayList<WorkBreak>());
	}

	@Override
	public Bundle getExtras() {
		return null;
	}

	@Override
	protected int getMenu() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
