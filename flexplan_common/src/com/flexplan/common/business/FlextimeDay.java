package com.flexplan.common.business;

import java.util.TreeSet;


public interface FlextimeDay {

	long getDate();

	long getStartTime();

	long getEndTime();

	long getTimeForBreaks();

	void setStartTime(long time);

	void setEndTime(long time);

	void addBreak(WorkBreak workbreak);

	void deleteBreak(WorkBreak workbreak);

	TreeSet<WorkBreak> getWorkBreaks();

	void setDate(long date);
}
