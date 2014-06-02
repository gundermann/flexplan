package com.flexplan.util;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

public class NumberPreference extends DialogPreference {

  private NumberPicker picker;

  public NumberPreference( Context context, AttributeSet attrs ) {
    super( context, attrs );

    setPositiveButtonText( "Set" );
    setNegativeButtonText( "Cancel" );
  }

  @Override
  protected View onCreateDialogView() {
    picker = new NumberPicker( getContext() );
    picker.setMinValue( 1 );
    picker.setMaxValue( 7 );
    return ( picker );
  }

  @Override
  protected void onBindDialogView( View v ) {
    super.onBindDialogView( v );
  }

  @Override
  protected void onDialogClosed( boolean positiveResult ) {
    super.onDialogClosed( positiveResult );

    if ( positiveResult ) {
      int number = picker.getValue();
      if ( callChangeListener( number ) ) {
        persistInt( number );
      }
    }
  }

}
