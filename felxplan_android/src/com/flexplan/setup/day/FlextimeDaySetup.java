package com.flexplan.setup.day;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.setup.TimeSetup;

public interface FlextimeDaySetup extends TimeSetup{
	
	FlextimeDay getFlextimeDay();

	void saveFlextimeDay();

	void updateDate(int day, int month, int year);
	
	void updateCache();

}
