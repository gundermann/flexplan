package com.flexplan.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.flexplan.BreakSetup;
import com.flexplan.BreakSetupDialog;
import com.flexplan.common.business.WorkBreak;

public class SaveBreakListener implements OnClickListener {

	private BreakSetupDialog breakSetupDialog;
	private BreakSetup setup;
	private WorkBreak workbreak;

	public SaveBreakListener(
			BreakSetupDialog breakSetupDialog, BreakSetup setup, WorkBreak workbreak) {
		this.breakSetupDialog = breakSetupDialog;
		this.setup = setup;
		this.workbreak = workbreak;
	}

	@Override
	public void onClick(DialogInterface paramDialogInterface, int paramInt) {
	setup.refreshBreakTime(workbreak, breakSetupDialog.getCurrentStartTime(), breakSetupDialog.getCurrentEndTime());
	}

}
