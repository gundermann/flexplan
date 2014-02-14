package com.flexplan;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.util.AbstractActivity;

public class OnFlextimeDayLongClickListener implements OnItemLongClickListener {

	private AbstractActivity activity;

	public OnFlextimeDayLongClickListener(AbstractActivity activity) {
		this.activity = activity;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		FlextimeDay day = (FlextimeDay) adapterView.getAdapter().getItem(
				position);
		if (activity instanceof DeleteProvider)
			DeleteDialog.newInstance((DeleteProvider) activity, day).show(
					activity.getSupportFragmentManager(), "LISTENER");
		return true;
	}

}
