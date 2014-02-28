package com.flexplan.util;

import android.view.View;
import android.view.View.OnClickListener;

import com.flexplan.FlextimeDaySetup;
import com.flexplan.persistence.FlextimeDBHelper;

public class SaveFlextimeDayListener implements OnClickListener {

	
	private FlextimeDBHelper dbHelper;
	private FlextimeDaySetup flextimeDaySetup;

	public SaveFlextimeDayListener(FlextimeDaySetup flextimeDaySetup, FlextimeDBHelper dbHelper) {
		this.flextimeDaySetup = flextimeDaySetup;
		this.dbHelper = dbHelper;
	}

	@Override
	public void onClick(View v) {
		dbHelper.insertOrUpdateFlextimeDay(flextimeDaySetup.getFlextimeDay());
	}

}
