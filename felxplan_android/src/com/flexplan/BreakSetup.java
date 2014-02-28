package com.flexplan;

import com.flexplan.common.business.WorkBreak;


public interface BreakSetup {

	void refreshBreakTime(WorkBreak workbreak, long startTime, long endTime);
	
	void initSettings(WorkBreak workbreak);
	
}
