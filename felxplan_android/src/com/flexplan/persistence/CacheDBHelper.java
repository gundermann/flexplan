package com.flexplan.persistence;

import java.util.List;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

public interface CacheDBHelper {

	long getStartTimeOfDay(String newDate);

	long getEndTimeOfDay(String newDate);

	void updateWorkBreaks(FlextimeDay currentFlextimeDay);

	void insertOrUpdateFlextimeDay(FlextimeDay flextimeDay);

	boolean isEmpty();

	String getCachedDate();

	void cleanup();

	FlextimeDay getCachedFlextimeDay();

	List<WorkBreak> getWorkBreaksForFlextimeDay(String newDate);

}
