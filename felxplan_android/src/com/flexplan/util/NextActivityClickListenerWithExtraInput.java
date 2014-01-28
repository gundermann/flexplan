package com.flexplan.util;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class NextActivityClickListenerWithExtraInput implements OnClickListener {

	private ExtraProvider activity;
	private Class<? extends AbstractActivityWithExtraInput> nextActivityClass;

	public NextActivityClickListenerWithExtraInput(
			ExtraProvider extraProvider,
			Class<? extends AbstractActivityWithExtraInput> nextActivityClass) {
				this.activity = extraProvider;
				this.nextActivityClass = nextActivityClass;
	}

	@Override
	public void onClick(View v) {
		//TODO use in NextActivityListened
		Intent intent = new Intent(v.getContext(), nextActivityClass);
		intent.putExtras(activity.getExtras());
		v.getContext().startActivity(intent);
	}

}
