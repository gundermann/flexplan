package com.flexplan.util;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class NextActivityClickListener implements OnClickListener {

	private Activity activity;
	private Class<? extends AbstractActivity> nextActivityClass;


	public NextActivityClickListener(
			Activity activity, Class<? extends AbstractActivity> nextActivityClass) {
				this.activity = activity;
				this.nextActivityClass = nextActivityClass;
	}


	@Override
	public void onClick(View v) {
		Intent nextIntent = new Intent(activity.getApplicationContext(), nextActivityClass);
		activity.startActivity(nextIntent);
	}

}
