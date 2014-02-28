package com.flexplan.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.flexplan.setup.TimeSetup;
import com.flexplan.setup.day.FlextimeDaySetup;

public class SetTimeOnFlextimeDayListener implements OnClickListener {

	private TimeSetup timeSetup;
	private FlextimeDaySetup daySetup;

	public SetTimeOnFlextimeDayListener(TimeSetup flextimeTimeSetup, FlextimeDaySetup flextimeDaySetup) {
		this.timeSetup = flextimeTimeSetup;
		this.daySetup = flextimeDaySetup;
	}

	@Override
	public void onClick(DialogInterface dialog, int arg1) {
		daySetup.updateTime(timeSetup.getCurrentStartTime(), timeSetup.getCurrentEndTime());
	}

}
