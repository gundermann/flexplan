package com.flexplan.common.business.impl;

import java.util.List;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

public class FlextimeDayImpl implements FlextimeDay{

	private long date;
	private long startTime;
	private long endTime;
	private List<WorkBreak> breaks;

	public FlextimeDayImpl(long date, long startTime, long endTime){
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	@Override
	public long getDate() {
		return date;
	}

	@Override
	public long getStartTime() {
		return startTime;
	}

	@Override
	public long getEndTime() {
		return endTime;
	}

	@Override
	public long getTimeForBreaks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStartTime(long time) {
		startTime = time;
	}

	@Override
	public void setEndTime(long time) {
		endTime = time;
	}

	@Override
	public void addBreak(WorkBreak workbreak) {
		breaks.add(workbreak);
	}

	@Override
	public void deleteBreak(WorkBreak workbreak) {
		breaks.clear();
	}
	

}
