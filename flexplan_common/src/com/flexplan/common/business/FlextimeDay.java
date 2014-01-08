package com.flexplan.common.business;


public interface FlextimeDay {

	long getDate();

	long getStartTime();

	long getEndTime();

	long getTimeForBreaks();

	void setStartTime(long time);

	void setEndTime(long time);

	void addBreak(WorkBreak workbreak);

	void deleteBreak(WorkBreak workbreak);
}
