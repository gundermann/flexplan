package com.felxplan;

import com.felxplan.persistence.DBHelper;
import com.flexplan.common.business.FlextimeDay;

import android.view.View;
import android.view.View.OnClickListener;

public class SaveFlextimeDayListener implements OnClickListener {

	
	private FlextimeDay currentFlextimeDay;
	private DBHelper dbHelper;

	public SaveFlextimeDayListener(FlextimeDay currentFlextimeDay, DBHelper dbHelper) {
		this.currentFlextimeDay = currentFlextimeDay;
		this.dbHelper = dbHelper;
	}

	@Override
	public void onClick(View v) {
		dbHelper.insertFlextimeDay(currentFlextimeDay);
	}

}
