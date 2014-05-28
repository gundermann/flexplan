package com.flexplan;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.util.DateHelper;

public class FlextimeOverviewAdapter extends ArrayAdapter<FlextimeDay> {

  TextView dayTv;

  TextView startTv;

  TextView endTv;

  public FlextimeOverviewAdapter( Context context, List<FlextimeDay> flextimeDays ) {
    super( context, R.layout.flextime_list, R.id.flextime_day, flextimeDays );
  }

  @Override
  public View getView( int position, View convertView, ViewGroup parent ) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    View rowView;
    FlextimeDay currentDay = getItem( position );
    if ( currentDay.isHoliday() ) {
      rowView = inflater.inflate( R.layout.flextime_list_holiday, parent, false );
    }
    else {
      rowView = inflater.inflate( R.layout.flextime_list, parent, false );
      startTv = (TextView) rowView.findViewById( R.id.flextime_start );
      endTv = (TextView) rowView.findViewById( R.id.flextime_end );
      startTv.setText( DateHelper.getTimeAsString( currentDay.getStartTime() ) );
      endTv.setText( DateHelper.getTimeAsString( currentDay.getEndTime() ) );
    }
    dayTv = (TextView) rowView.findViewById( R.id.flextime_day );
    dayTv.setText( DateHelper.getDayOfWeekByDateAsString( currentDay.getDate() ) );

    return rowView;
  }

}
