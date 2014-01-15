package com.felxplan;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ShowBreakListener implements OnClickListener {

	private Context context;
	private FlextimeDaySetup flextimeDaySetup;

	public ShowBreakListener(Context context, FlextimeDaySetup flextimeDaySetup) {
		this.context = context;
		this.flextimeDaySetup = flextimeDaySetup;
		

	}

	@Override
	public void onClick(View view) {
		flextimeDaySetup.saveFlextimeDay();
		context.startActivity(new Intent(context, BreakViewActivity.class));
	}

}
