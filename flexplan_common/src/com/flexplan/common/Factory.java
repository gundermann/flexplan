package com.flexplan.common;

import java.util.List;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.common.business.impl.FlextimeDayImpl;
import com.flexplan.common.business.impl.WorkBreakImpl;

public class Factory {

  public static Factory getInstance() {
    return new Factory();
  }

  public WorkBreak createWorkBreak( long startTime, long endTime ) {
    return new WorkBreakImpl( startTime, endTime );
  }

  @Deprecated
  public FlextimeDay createFlextimeDay( String date, long startTime, long endTime, List<WorkBreak> breaks ) {
    FlextimeDay flextimeDay = new FlextimeDayImpl( date, startTime, endTime );

    for ( WorkBreak workBreak : breaks ) {
      flextimeDay.addBreak( workBreak );
    }

    return flextimeDay;
  }

  public FlextimeDay createFlextimeDay( String date, long startTime, long endTime, List<WorkBreak> breaks,
      boolean isHoliday ) {
    FlextimeDay flextimeDay = new FlextimeDayImpl( date, startTime, endTime, isHoliday );

    for ( WorkBreak workBreak : breaks ) {
      flextimeDay.addBreak( workBreak );
    }

    return flextimeDay;
  }

}
