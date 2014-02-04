package com.flexplan.persistence;

import java.util.List;

import com.flexplan.common.business.FlextimeDay;

public interface FlextimeDB {

	void insertOrUpdateFlextimeDay(FlextimeDay flextimeDay);
	
	List<FlextimeDay> getAllFlextimeDays();

	List<FlextimeDay> getCurrentWeekDays(int weekOfYear, int year);

	void insertWorkBreaks(FlextimeDay currentFlextimeDay);

	void delete(FlextimeDay flextimeDay);
}
