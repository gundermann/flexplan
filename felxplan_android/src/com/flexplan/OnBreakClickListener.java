package com.flexplan;

import com.flexplan.common.business.WorkBreak;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnBreakClickListener implements OnItemClickListener {

	private BreakSetup breakSetup;

	public OnBreakClickListener(BreakSetup breakSetup) {
		this.breakSetup = breakSetup;
	}

	@Override
	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong) {
		WorkBreak workBreak = (WorkBreak) paramAdapterView.getAdapter().getItem(paramInt);
		breakSetup.initSettings(workBreak);
	}

}
