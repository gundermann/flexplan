package com.flexplan.util;

import android.content.Intent;
import android.os.Bundle;



public abstract class AbstractActivityExtraProvider extends AbstractActivity {

	protected void startNextActivitWithExtras(
			Class<? extends AbstractActivity> nextActivity) {
		Intent intent = new Intent(getApplicationContext(), nextActivity);
		intent.putExtras(getExtras());
		super.startNextActivity(intent);
	}

	abstract protected Bundle getExtras();
}
