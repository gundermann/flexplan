package com.flexplan;

import android.view.View;
import android.view.View.OnClickListener;

public class WeekChangeListener implements OnClickListener {

	private FlextimeOverviewActivity flextimeOverviewActivity;
	private int newWeek;

	public WeekChangeListener(
			FlextimeOverviewActivity flextimeOverviewActivity, int newWeek) {
				this.flextimeOverviewActivity = flextimeOverviewActivity;
				this.newWeek = newWeek;
	}

	@Override
	public void onClick(View view) {
		flextimeOverviewActivity.setCurrentWeek(newWeek);
	}

}
