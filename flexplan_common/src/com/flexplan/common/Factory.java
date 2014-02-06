package com.flexplan.common;

import java.util.List;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.business.impl.FlextimeDayImpl;
import com.flexplan.common.business.impl.WorkBreakImpl;
import com.flexplan.common.util.DateHelper;

public class Factory {

	public static Factory getInstance(){
		return new Factory();
	}
	
	public WorkBreak createWorkBreak(long startTime, long endTime){
		return new WorkBreakImpl(startTime, endTime);
	}
	

	public FlextimeDay createFlextimeDay(String date, long startTime, long endTime, List<WorkBreak> breaks){
		FlextimeDay flextimeDay = new FlextimeDayImpl(date, startTime, endTime);
		
		for(WorkBreak workBreak : breaks){
			flextimeDay.addBreak(workBreak);
		}
		
		return flextimeDay;
	}

	public FlextimeDay createFreeDayOfWeek(int dayOfWeek, int weekOfYear, int year) {
		return new FlextimeDayImpl(DateHelper.getDateByWeekOfYearAsString(dayOfWeek, weekOfYear, year), 0, 0);
	}
}
