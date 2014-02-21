package com.flexplan;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.flexplan.common.business.FlextimeDay;

public class OnFlextimeDayClickListener implements OnItemClickListener {

	private AbstractFlextimeActivity activity;

	public OnFlextimeDayClickListener(AbstractFlextimeActivity callingActivity) {
		activity = callingActivity;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		FlextimeDay day = (FlextimeDay) adapterView.getAdapter().getItem(position);
		activity.setFlextimeDay(day);
		activity.updateCache();
		Intent intent = new Intent(activity, FlextimeDaySetupActivity.class);
		activity.startActivity(intent);
	}
}
