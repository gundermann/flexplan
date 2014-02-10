package com.flexplan;


import com.flexplan.common.business.FlextimeDay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnFlextimeDayClickListener implements OnItemClickListener {

	private Context context;

	public OnFlextimeDayClickListener(Activity callingActivity) {
		context = callingActivity;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		FlextimeDay day = (FlextimeDay) adapterView.getAdapter().getItem(position);
		Intent intent = new Intent(context, FlextimeDaySetupActivity.class);
		intent.putExtra("date", day.getDate());
		context.startActivity(intent);
	}

}
