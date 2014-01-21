package com.flexplan;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.felxplan.R;
import com.flexplan.common.business.FlextimeDay;

public class FlextimeOverviewAdapter extends ArrayAdapter<FlextimeDay> {


	public FlextimeOverviewAdapter(Context context, List<FlextimeDay> flextimeDays) {
		super(context, R.layout.flextime_list, flextimeDays);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return super.getView(position, convertView, parent);
	}

	

}
