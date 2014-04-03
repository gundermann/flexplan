package com.flexplan.setup.breaks;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.setup.TimeSetup;

public class BreakTimeSetup implements TimeSetup {

	private WorkBreak workBreak;
	private BreakSetup setup;

	public BreakTimeSetup(WorkBreak workBreak, BreakSetup setup) {
		this.workBreak = workBreak;
		this.setup = setup;
	}

	@Override
	public long getStartTime() {
		return workBreak.getStartTime();
	}

	@Override
	public long getEndTime() {
		return workBreak.getEndTime();
	}

	@Override
	public void setTime(long startTime, long endTime) {
		setup.refreshBreakTime(workBreak, startTime, endTime);
	}

}
