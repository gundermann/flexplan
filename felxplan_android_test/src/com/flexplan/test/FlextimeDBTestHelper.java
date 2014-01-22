package com.flexplan.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.flexplan.common.FlextimeDayFactory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.persistence.FlextimeDB;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class FlextimeDBTestHelper {
	
	public static FlextimeDB createFlextimeDB(){
		FlextimeDB dbMock = mock(FlextimeDB.class);
		
		GregorianCalendar cal = new GregorianCalendar();
		int currentWeek = cal.get(GregorianCalendar.WEEK_OF_YEAR);
		int currentYear = cal.get(GregorianCalendar.YEAR);
		
		List<FlextimeDay> weekList = createHolidayWeek(currentWeek, currentYear);
		
		when(dbMock.getCurrentWeekDays(currentWeek, currentYear)).thenReturn(weekList);
		return dbMock;
	}

	private static List<FlextimeDay> createHolidayWeek(int currentWeek, int currentYear) {
		List<FlextimeDay> holidayWeekList = new ArrayList<FlextimeDay>();
		for(int i = 1; i <= 7; i++){
			holidayWeekList.add(FlextimeDayFactory.createFreeDayOfWeek(i, currentWeek, currentYear));
		}
		return holidayWeekList;
	}

}
