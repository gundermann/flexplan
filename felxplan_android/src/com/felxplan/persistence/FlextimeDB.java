package com.felxplan.persistence;

import com.flexplan.common.business.FlextimeDay;
import java.util.List;

public interface FlextimeDB {

	void insertFlextimeDay(FlextimeDay flextimeDay);
	
	List<FlextimeDay> getAllFlextimeDays();

	List<FlextimeDay> getCurrentWeek(int weekOfYear, int year);
}
