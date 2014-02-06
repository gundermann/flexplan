package com.flexplan.common.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

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
	
	private TreeSet<WorkBreak> breaks;

	public FlextimeDayImpl(String date, long startTime, long endTime){
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		breaks = new TreeSet<WorkBreak>();
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
		Iterator<WorkBreak> iterator = breaks.iterator();
		while (iterator.hasNext()) {
			time =+ iterator.next().getLength();
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
		breaks.clear();
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
	

}
