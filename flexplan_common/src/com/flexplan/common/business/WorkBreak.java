package com.flexplan.common.business;

public interface WorkBreak extends Comparable<WorkBreak> {

	long getStartTime();

	long getEndTime();

	long getLength();

	void setStartTime(long time);

	void setEndTime(long time);

}
