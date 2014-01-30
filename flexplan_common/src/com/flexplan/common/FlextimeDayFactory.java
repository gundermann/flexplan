package com.flexplan.common;

import java.util.List;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.business.impl.FlextimeDayImpl;
import com.flexplan.common.util.DateHelper;


public class FlextimeDayFactory {

	public static FlextimeDay createFlextimeDay(long date, long startTime, long endTime, List<WorkBreak> breaks){
		FlextimeDay flextimeDay = new FlextimeDayImpl(date, startTime, endTime);
		
		for(WorkBreak workBreak : breaks){
			flextimeDay.addBreak(workBreak);
		}
		
		return flextimeDay;
	}

	public static FlextimeDay createFreeDayOfWeek(int dayOfWeek, int weekOfYear, int year) {
		return new FlextimeDayImpl(DateHelper.convertToLongByWeekOfYear(dayOfWeek, weekOfYear, year), 0, 0);
	}
}
