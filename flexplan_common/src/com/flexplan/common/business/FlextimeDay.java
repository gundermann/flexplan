package com.flexplan.common.business;

import java.util.List;


public interface FlextimeDay {

	String getDate();

	long getStartTime();

	long getEndTime();

	long getTimeForBreaks();

	void setStartTime(long time);

	void setEndTime(long time);

	void addBreak(WorkBreak workbreak);

	void deleteBreak(WorkBreak workbreak);

	List<WorkBreak> getWorkBreaks();

	void setDate(String date);

	long getLenght();
}
