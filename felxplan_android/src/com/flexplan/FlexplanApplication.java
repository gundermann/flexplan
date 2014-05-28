package com.flexplan;

import java.util.List;

import android.app.Application;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.persistence.CacheDBHelper;
import com.flexplan.persistence.CacheDBHelperImpl;
import com.flexplan.persistence.FlextimeDBHelper;
import com.flexplan.persistence.FlextimeDBHelperImpl;

public class FlexplanApplication extends Application {

  private FlextimeDBHelperImpl flextimeDbHelper;

  private CacheDBHelperImpl cacheDbHelper;

  private FlextimeDay currentFlextimeDay;

  private FlextimeDBHelper getFlextimeDB() {
    if ( flextimeDbHelper == null )
      flextimeDbHelper = new FlextimeDBHelperImpl( this );
    return flextimeDbHelper;
  }

  public FlextimeDay getcurrentDay() {
    return currentFlextimeDay;
  }

  public CacheDBHelper getCacheDB() {
    if ( cacheDbHelper == null )
      cacheDbHelper = new CacheDBHelperImpl( this );
    return cacheDbHelper;
  }

  public boolean isDateCached( String newDate ) {
    return cacheDbHelper.getCachedDate() != null ? cacheDbHelper.getCachedDate().equals( newDate ) : false;
  }

  public boolean existsCacheData() {
    return !getCacheDB().isEmpty();
  }

  public List<FlextimeDay> getCurrentWeekDays( int currentWeek, int currentYear ) {
    return getFlextimeDB().getCurrentWeekDays( currentWeek, currentYear );
  }

  public void delete( FlextimeDay flextimeDay ) {
    getFlextimeDB().delete( flextimeDay );
  }

  public void updateCache() {
    getCacheDB().insertOrUpdateFlextimeDay( currentFlextimeDay );
  }

  public void setFlextimeDay( FlextimeDay flextimeDay ) {
    this.currentFlextimeDay = flextimeDay;
  }

  public boolean isDateInDB() {
    return getFlextimeDB().isDateInDB( currentFlextimeDay.getDate() );
  }

  public void deleteDay() {
    getFlextimeDB().delete( currentFlextimeDay );
  }

  public void insertOrUpdateFlextimeDay() {
    getFlextimeDB().insertOrUpdateFlextimeDay( currentFlextimeDay );
    getCacheDB().cleanup();
  }

  public boolean isDateInDB( String newDate ) {
    return getFlextimeDB().isDateInDB( newDate );
  }

  public long getStartTimeOfDay( String newDate ) {
    return getFlextimeDB().getStartTimeOfDay( newDate );
  }

  public long getEndTimeOfDay( String newDate ) {
    return getFlextimeDB().getEndTimeOfDay( newDate );
  }

  public boolean isHoliday( String newDate ) {
    return getFlextimeDB().isHoliday( newDate );
  }

}
