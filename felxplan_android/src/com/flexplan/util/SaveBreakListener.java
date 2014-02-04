package com.flexplan.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.flexplan.FlextimeDaySetup;
import com.flexplan.TimeSetup;

public class SaveBreakListener implements OnClickListener {

	private TimeSetup breakSetup;
	private FlextimeDaySetup flextimeDaySetup;

	public SaveBreakListener(TimeSetup breakSetupDialog,
			FlextimeDaySetup flextimeDaySetup) {
		this.breakSetup = breakSetupDialog;
		this.flextimeDaySetup = flextimeDaySetup;
	}

	@Override
	public void onClick(DialogInterface dialog, int arg1) {
		flextimeDaySetup.addBreak(breakSetup.getCurrentStartTime(),
				breakSetup.getCurrentEndTime());
		
	}

}
