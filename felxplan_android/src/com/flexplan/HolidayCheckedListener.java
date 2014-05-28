package com.flexplan;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.flexplan.setup.day.FlextimeDaySetup;

public class HolidayCheckedListener implements OnCheckedChangeListener {

  private final FlextimeDaySetup flextimeDaySetup;

  public HolidayCheckedListener( FlextimeDaySetup flextimeDaySetup ) {
    this.flextimeDaySetup = flextimeDaySetup;
  }

  @Override
  public void onCheckedChanged( CompoundButton cb, boolean checked ) {
    flextimeDaySetup.setHoliday( checked );
  }

}
