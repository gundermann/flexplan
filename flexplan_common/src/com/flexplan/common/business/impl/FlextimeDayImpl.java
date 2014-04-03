package com.flexplan.common.business.impl;

import java.util.ArrayList;
import java.util.List;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.util.Column;
import com.flexplan.common.util.Table;

@Table(Name = "flextime")
public class FlextimeDayImpl implements FlextimeDay{

	@Column(ID=true)
	private String date;
	
	@Column(Type="numeric")
	private long startTime;
	
	@Column(Type="numeric")
	private long endTime;
	
	private List<WorkBreak> breaks;

	public FlextimeDayImpl(String date, long startTime, long endTime){
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		breaks = new ArrayList<WorkBreak>();
	}
	
	@Override
	public String getDate() {
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
		long time = 0L;
		for(Object workbreak :  breaks.toArray()){
			time += ((WorkBreak) workbreak).getLength();
		}
		return time;
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
		breaks.remove(workbreak);
	}

	@Override
	public List<WorkBreak> getWorkBreaks() {
		List<WorkBreak> breakList = new ArrayList<WorkBreak>();
		breakList.addAll(breaks);
		return breakList;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public long getLenght() {
		return endTime-startTime-getTimeForBreaks();
	}
	

}
