package com.flexplan;

import com.flexplan.common.business.FlextimeDay;

public interface FlextimeDaySetup {
	
	FlextimeDay getFlextimeDay();

	void saveFlextimeDay();

	void updateTime(long startTime, long endTime);

}
