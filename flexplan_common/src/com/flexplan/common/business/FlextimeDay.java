package com.flexplan.common.business;

import java.util.Date;
import java.util.List;


public interface FlextimeDay {

	Date getDate();

	long getStartTime();

	long getEndTime();

	long getTimeForBreaks();

	void setStartTime(long time);

	void setEndTime(long time);

	void addBreak(WorkBreak workbreak);

	void deleteBreak(WorkBreak workbreak);

	List<WorkBreak> getWorkBreaks();

	void setDate(Date date);
}
