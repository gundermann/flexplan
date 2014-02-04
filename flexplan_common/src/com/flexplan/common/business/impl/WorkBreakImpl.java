package com.flexplan.common.business.impl;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.Column;
import com.flexplan.common.util.Table;

@Table(Name="break", ForeignTable="flextime")
public class WorkBreakImpl implements WorkBreak {

	@Column(Type="numeric", ID=true)
	private long startTime;
	
	private long endTime;

	public WorkBreakImpl(long startTime, long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
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
	public void setStartTime(long time) {
		startTime = time;
	}

	@Override
	public void setEndTime(long time) {
		endTime = time;
	}

	@Override
	public int compareTo(WorkBreak workBreak) {
		if (this.startTime < workBreak.getStartTime()) {
			return -1;
		} else if (this.startTime > workBreak.getStartTime()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public long getLength() {
		return endTime - startTime;
	}

}
