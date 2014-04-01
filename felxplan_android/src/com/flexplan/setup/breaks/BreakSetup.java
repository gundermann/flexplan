package com.flexplan.setup.breaks;

import com.flexplan.common.business.WorkBreak;


public interface BreakSetup {

	void refreshBreakTime(WorkBreak workbreak, long startTime, long endTime);
	
}
