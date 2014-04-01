package com.flexplan.setup.breaks;

import com.flexplan.common.business.WorkBreak;
import com.flexplan.setup.SaveDiscardProvider;
import com.flexplan.util.DeleteProvider;
import com.flexplan.util.OverwriteProvider;

public interface BreakSetup extends SaveDiscardProvider, OverwriteProvider,
		DeleteProvider<WorkBreak> {

	void refreshBreakTime(WorkBreak workbreak, long startTime, long endTime);

}
