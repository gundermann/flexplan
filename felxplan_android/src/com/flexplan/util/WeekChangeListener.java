package com.flexplan.util;

import com.flexplan.FlextimeOverviewActivity;

import android.view.View;
import android.view.View.OnClickListener;

public class WeekChangeListener implements OnClickListener {

	private FlextimeOverviewActivity flextimeOverviewActivity;
	private int weekChange;

	public WeekChangeListener(
			FlextimeOverviewActivity flextimeOverviewActivity, int weekChange) {
		this.flextimeOverviewActivity = flextimeOverviewActivity;
		this.weekChange = weekChange;
	}

	@Override
	public void onClick(View view) {
		flextimeOverviewActivity.setCurrentWeek(flextimeOverviewActivity
				.getCurrentWeek() + weekChange);
	}

}
