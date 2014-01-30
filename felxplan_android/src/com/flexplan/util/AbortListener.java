package com.flexplan.util;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class AbortListener implements OnClickListener {

	private Activity activity;

	public AbortListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View paramView) {
		activity.onBackPressed();
	}

}
