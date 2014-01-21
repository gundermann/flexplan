package com.flexplan;

import android.view.View;
import android.view.View.OnClickListener;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.persistence.FlextimeDB;

public class SaveBreakListener implements OnClickListener {

	private WorkBreak currentBreak;
	private FlextimeDB dbHelper;
	private long currentDate;

	public SaveBreakListener(FlextimeDB dbHelper,
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
