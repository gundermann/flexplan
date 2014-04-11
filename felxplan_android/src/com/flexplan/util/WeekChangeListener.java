package com.flexplan.util;

import android.view.View;
import android.view.View.OnClickListener;

import com.flexplan.FlextimeOverviewActivity;

public class WeekChangeListener implements OnClickListener {

	private FlextimeOverviewActivity flextimeOverview;
	private int weekChange;

	public WeekChangeListener(
			FlextimeOverviewActivity flextimeOverviewFragment, int weekChange) {
		this.flextimeOverview = flextimeOverviewFragment;
		this.weekChange = weekChange;
	}

	@Override
	public void onClick(View view) {
		flextimeOverview.switchToWeek(weekChange);
	}

}
