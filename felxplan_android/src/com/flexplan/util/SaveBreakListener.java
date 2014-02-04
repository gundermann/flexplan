package com.flexplan.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.flexplan.BreakSetup;
import com.flexplan.TimeSetup;

public class SaveBreakListener implements OnClickListener {

	private TimeSetup breakTimeSetup;
	private BreakSetup breakSetup;

	public SaveBreakListener(TimeSetup breakSetupDialog,
			BreakSetup breakSetup) {
		this.breakTimeSetup = breakSetupDialog;
		this.breakSetup = breakSetup;
	}

	@Override
	public void onClick(DialogInterface dialog, int arg1) {
		breakSetup.addBreak(breakTimeSetup.getCurrentStartTime(),
				breakTimeSetup.getCurrentEndTime());
		
	}

}
