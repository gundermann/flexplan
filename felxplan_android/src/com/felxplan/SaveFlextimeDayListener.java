package com.felxplan;

import com.felxplan.persistence.DBHelper;
import com.flexplan.common.business.FlextimeDay;

import android.view.View;
import android.view.View.OnClickListener;

public class SaveFlextimeDayListener implements OnClickListener {

	
	private DBHelper dbHelper;
	private FlextimeDaySetup flextimeDaySetup;

	public SaveFlextimeDayListener(FlextimeDaySetup flextimeDaySetup, DBHelper dbHelper) {
		this.flextimeDaySetup = flextimeDaySetup;
		this.dbHelper = dbHelper;
	}

	@Override
	public void onClick(View v) {
		
		dbHelper.insertFlextimeDay(flextimeDaySetup.getFlextimeDay());
	}

}
