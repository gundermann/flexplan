package com.flexplan.setup.day;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.flexplan.AbstractFlextimeActivity;
import com.flexplan.BreakOverviewActivity;
import com.flexplan.FlexplanApplication;
import com.flexplan.HolidayCheckedListener;
import com.flexplan.ListenerFactory;
import com.flexplan.R;
import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.util.DateHelper;
import com.flexplan.persistence.StartTimeSetupListener;
import com.flexplan.setup.EndTimeSetupListener;
import com.flexplan.setup.SaveDiscardProvider;
import com.flexplan.util.DateChangedListener;
import com.flexplan.util.OverwriteProvider;
import com.flexplan.util.SaveOrDiscardDialog;
import com.flexplan.util.SimpleDialog;

public class FlextimeDaySetupActivity extends AbstractFlextimeActivity implements FlextimeDaySetup,
    SaveDiscardProvider, OverwriteProvider {

  private static final String TAG = null;

  private DatePicker date;

  private TextView dateTv;

  private Button timeFromBT;

  private Button timeToBT;

  private CheckBox holidayCB;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
  }

  @Override
  protected void onResume() {
    super.onResume();
    if ( !existsCacheData() ) {
      updateDate( DateHelper.getCurrentDayOfMonth(), DateHelper.getCurrentMonth(), DateHelper.getCurrentYear() );
    }
  }

  private boolean existsCacheData() {
    if ( ( (FlexplanApplication) getApplication() ).existsCacheData() ) {
      String dateString = ( (FlexplanApplication) getApplication() ).getCacheDB().getCachedFlextimeDay().getDate();
      date.getCalendarView().setDate( DateHelper.convertDateStringToLong( dateString ) );
      updateDate( Integer.valueOf( dateString.substring( 0, 2 ) ), Integer.valueOf( dateString.substring( 3, 5 ) ),
          Integer.valueOf( dateString.substring( 6 ) ) );
      return true;
    }
    return false;
  }

  private void setupFlextimeDay( String newDate, long startTime, long endTime, boolean isHoliday ) {
    app.setFlextimeDay( Factory.getInstance().createFlextimeDay( newDate, startTime, endTime,
        getCacheDbHelper().getWorkBreaksForFlextimeDay( newDate ), isHoliday ) );
    updateDateTv();
    updateTimeBTs();
    updateHolidayCB();
    updateCache();
  }

  private void updateHolidayCB() {
    holidayCB.setChecked( app.getcurrentDay().isHoliday() );
  }

  @Override
  public void saveFlextimeDay() {
    save();
  }

  @Override
  protected void setContentView() {
    setContentView( R.layout.activity_flextime_day_setup );
  }

  @Override
  protected void initElements() {
    date = (DatePicker) findViewById( R.id.day );
    date.getCalendarView().setOnDateChangeListener( new DateChangedListener( this ) );
    dateTv = (TextView) findViewById( R.id.day_tv );
    timeFromBT = (Button) findViewById( R.id.start_time );
    timeFromBT.setOnClickListener( new StartTimeSetupListener( this, this ) );
    timeToBT = (Button) findViewById( R.id.end_time );
    timeToBT.setOnClickListener( new EndTimeSetupListener( this, this ) );
    holidayCB = (CheckBox) findViewById( R.id.holiday );
    holidayCB.setOnCheckedChangeListener( new HolidayCheckedListener( this ) );
  }

  @Override
  protected int getMenu() {
    return R.menu.flextime_day_setup_menu;
  }

  @Override
  public boolean onOptionsItemSelected( MenuItem item ) {
    switch ( item.getItemId() ) {
      case R.id.save: {
        save();
        break;
      }
      case R.id.show_breaks: {
        startNextActivity( BreakOverviewActivity.class );
        break;
      }
      case R.id.abort: {
        onBackPressed();
        break;
      }
      default:
        break;
    }
    return super.onOptionsItemSelected( item );
  }

  @Override
  public void onBackPressed() {
    SaveOrDiscardDialog.newInstance( this ).show( getSupportFragmentManager(), TAG );
  }

  @Override
  public void save() {
    if ( app.isDateInDB() ) {
      SimpleDialog.newInstance( ListenerFactory.createOverrideListener( this ), getString( R.string.override_day ) )
          .show( getSupportFragmentManager(), TAG );
    }
    else {
      overwriteOrSave();
    }
  }

  // FIXME
  @Override
  public void overwriteOrSave() {
    app.deleteDay();
    app.insertOrUpdateFlextimeDay();
    super.onBackPressed();
  }

  @Override
  public void discard() {
    ( (FlexplanApplication) getApplication() ).getCacheDB().cleanup();
    super.onBackPressed();
  }

  @Override
  public void updateDate( int day, int month, int year ) {
    String newDate = DateHelper.getDateAsString( day, month, year );
    long startTime = DateHelper.DAY_START;
    long endTime = DateHelper.DAY_END;
    boolean isHoliday = false;
    if ( ( (FlexplanApplication) getApplication() ).isDateCached( newDate ) ) {
      startTime = ( (FlexplanApplication) getApplication() ).getCacheDB().getStartTimeOfDay( newDate );
      endTime = ( (FlexplanApplication) getApplication() ).getCacheDB().getEndTimeOfDay( newDate );
      isHoliday = ( (FlexplanApplication) getApplication() ).getCacheDB().isHoliday( newDate );
    }

    else if ( app.isDateInDB( newDate ) ) {
      startTime = app.getStartTimeOfDay( newDate );
      endTime = app.getEndTimeOfDay( newDate );
      isHoliday = app.isHoliday( newDate );
    }
    setupFlextimeDay( newDate, startTime, endTime, isHoliday );
  }

  private void updateTimeBTs() {
    timeFromBT.setText( DateHelper.getTimeAsString( app.getcurrentDay().getStartTime() ) );
    timeToBT.setText( DateHelper.getTimeAsString( app.getcurrentDay().getEndTime() ) );

  }

  private void updateDateTv() {
    dateTv.setText( app.getcurrentDay().getDate() );
  }

  @Override
  public String getSaveDiscardMessage() {
    return getString( R.string.save_or_discard_day );
  }

  @Override
  public long getStartTime() {
    return app.getcurrentDay().getStartTime();
  }

  @Override
  public long getEndTime() {
    return app.getcurrentDay().getEndTime();
  }

  @Override
  public void setTime( long startTime, long endTime ) {
    setupFlextimeDay( app.getcurrentDay().getDate(), startTime, endTime, app.getcurrentDay().isHoliday() );
  }

  @Override
  public FlextimeDay getFlextimeDay() {
    return app.getcurrentDay();
  }

  @Override
  public void updateCache() {
    app.updateCache();
  }

  @Override
  public boolean isHoliday() {
    return app.getcurrentDay().isHoliday();
  }

  @Override
  public void setHoliday( boolean isHoliday ) {
    setupFlextimeDay( app.getcurrentDay().getDate(), app.getcurrentDay().getStartTime(), app.getcurrentDay()
        .getEndTime(), isHoliday );
  }
}
