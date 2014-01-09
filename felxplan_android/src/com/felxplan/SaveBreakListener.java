package com.felxplan;

import com.felxplan.persistence.DBHelper;
import com.flexplan.common.business.WorkBreak;

import android.view.View;
import android.view.View.OnClickListener;

public class SaveBreakListener implements OnClickListener {

	private WorkBreak currentBreak;
	private DBHelper dbHelper;
	private long currentDate;

	public SaveBreakListener(DBHelper dbHelper,
			WorkBreak currentBreak, long currentDate) {
				this.dbHelper = dbHelper;
				this.currentBreak = currentBreak;
				this.currentDate = currentDate;
		
	}

	@Override
	public void onClick(View view) {
		dbHelper.insertWorkBreak(currentBreak, currentDate);
	}

}
