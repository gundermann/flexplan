package com.flexplan.util;

import android.view.View;
import android.view.View.OnClickListener;

import com.flexplan.FlextimeDaySetup;
import com.flexplan.persistence.FlextimeDB;

public class SaveFlextimeDayListener implements OnClickListener {

	
	private FlextimeDB dbHelper;
	private FlextimeDaySetup flextimeDaySetup;

	public SaveFlextimeDayListener(FlextimeDaySetup flextimeDaySetup, FlextimeDB dbHelper) {
		this.flextimeDaySetup = flextimeDaySetup;
		this.dbHelper = dbHelper;
	}

	@Override
	public void onClick(View v) {
		dbHelper.insertOrUpdateFlextimeDay(flextimeDaySetup.getFlextimeDay());
	}

}
