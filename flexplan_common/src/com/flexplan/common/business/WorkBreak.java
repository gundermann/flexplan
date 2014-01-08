package com.flexplan.common.business;

public interface WorkBreak extends Comparable<WorkBreak>{

	long getStartTime();

	long getEndTime();

	void setStartTime(long time);

	void setEndTime(long time);
}
