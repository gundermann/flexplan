package com.flexplan.test.support;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.TreeSet;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;


public class FlextimeDayTestSupport {

	public static FlextimeDay createDefaultTuesday() {
		FlextimeDay day = mock(FlextimeDay.class);
		
		when(day.getDate());
		when(day.getStartTime());
		when(day.getEndTime());
		when(day.getTimeForBreaks());
		when(day.getWorkBreaks()).thenReturn(new TreeSet<WorkBreak>());
		return day;
	}


}
