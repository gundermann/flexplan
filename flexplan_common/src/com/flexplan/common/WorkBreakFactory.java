package com.flexplan.common;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.business.impl.WorkBreakImpl;

public class WorkBreakFactory {

	public static WorkBreak createWorkBreak(long startTime, long endTime){
		return new WorkBreakImpl(startTime, endTime);
	}
}
