package com.flexplan;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

  public static final String BREAK_TIME = "breaktime";

  public static final String END_TIME = "end_time";

  public static final String DAYS_PER_WEEK = "days_per_week";

  public static final String HOURS_PER_WEEK = "hours_per_week";

  @Override
  protected void onPostCreate( Bundle savedInstanceState ) {
    super.onPostCreate( savedInstanceState );

    setupSimplePreferencesScreen();
  }

  @SuppressWarnings( "deprecation" )
  private void setupSimplePreferencesScreen() {
    addPreferencesFromResource( R.xml.flexplan_preference );
  }
}