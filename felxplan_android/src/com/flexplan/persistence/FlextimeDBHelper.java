package com.flexplan.persistence;

import java.util.List;

import com.flexplan.common.business.FlextimeDay;

public interface FlextimeDBHelper {

	void insertOrUpdateFlextimeDay(FlextimeDay flextimeDay);
	
	List<FlextimeDay> getAllFlextimeDays();

	List<FlextimeDay> getCurrentWeekDays(int weekOfYear, int year);

	boolean insertWorkBreaks(FlextimeDay currentFlextimeDay);

	boolean delete(FlextimeDay flextimeDay);

	boolean isDateInDB(String newDate);

	long getStartTimeOfDay(String newDate);

	long getEndTimeOfDay(String newDate);
}
