package com.felxplan;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ShowBreakListener implements OnClickListener {

	private Context applicationContext;

	public ShowBreakListener(Context applicationContext) {
		this.applicationContext = applicationContext;

	}

	@Override
	public void onClick(View view) {
		applicationContext.startActivity(new Intent(applicationContext, BreakViewActivity.class));
	}

}
