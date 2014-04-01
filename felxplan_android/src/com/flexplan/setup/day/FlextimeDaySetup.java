package com.flexplan.setup.day;

import com.flexplan.common.business.FlextimeDay;

public interface FlextimeDaySetup extends TimeSetup{
	
	FlextimeDay getFlextimeDay();

	void saveFlextimeDay();

	void updateDate(int day, int month, int year);
	
	void updateCache();

}
