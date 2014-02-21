package com.flexplan.persistence;

import com.flexplan.common.business.FlextimeDay;

public interface CacheDBHelper {

	long getStartTimeOfDay(String newDate);

	long getEndTimeOfDay(String newDate);

	void insertWorkBreaks(FlextimeDay currentFlextimeDay);

	void insertOrUpdateFlextimeDay(FlextimeDay flextimeDay);

	boolean isEmpty();

	String getCachedDate();

	void cleanup();

	FlextimeDay getCachedFlextimeDay();

}
