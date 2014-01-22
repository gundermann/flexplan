package com.flexplan.persistence;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

import java.util.List;

public interface FlextimeDB {

	void insertFlextimeDay(FlextimeDay flextimeDay);
	
	List<FlextimeDay> getAllFlextimeDays();

	List<FlextimeDay> getCurrentWeekDays(int weekOfYear, int year);

	void insertWorkBreak(WorkBreak currentBreak, long currentDate);
}
