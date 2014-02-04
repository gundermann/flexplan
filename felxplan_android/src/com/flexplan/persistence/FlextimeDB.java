package com.flexplan.persistence;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

import java.util.List;

public interface FlextimeDB {

	void insertOrUpdateFlextimeDay(FlextimeDay flextimeDay);
	
	List<FlextimeDay> getAllFlextimeDays();

	List<FlextimeDay> getCurrentWeekDays(int weekOfYear, int year);

	void insertWorkBreak(WorkBreak currentBreak, long currentDate);

	void insertWorkBreaks(FlextimeDay currentFlextimeDay);

	void delete(FlextimeDay flextimeDay);
}
