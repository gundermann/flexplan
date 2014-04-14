package com.flexplan;

import java.util.List;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.util.DateHelper;
import com.flexplan.setup.ChangeProvider;
import com.flexplan.setup.day.FlextimeDaySetupActivity;
import com.flexplan.util.AbstractActivity;
import com.flexplan.util.DeleteProvider;
import com.flexplan.util.SimpleDialog;

public class FlextimeOverviewFragment extends Fragment implements
		DeleteProvider<FlextimeDay>, ChangeProvider<FlextimeDay> {

	private static final String TAG = FlextimeOverviewFragment.class.getSimpleName();

	private int currentWeek;

	private TextView week;

	private TextView hoursThisWeekTv;

	private ListView flextimeWeekList;

	private int currentYear;

	protected SharedPreferences prefs;

	public static Fragment newInstance(int currentWeek, int currentYear) {
		FlextimeOverviewFragment fragment = new FlextimeOverviewFragment();
		fragment.currentWeek = currentWeek;
		fragment.currentYear = currentYear;
		return fragment;
	}

	@Override
	public void onResume() {
		super.onResume();
		updateListView();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		View rootView = inflater.inflate(R.layout.fragement_flextime_overview,
				container, false);
		week = (TextView) rootView.findViewById(R.id.week);
		hoursThisWeekTv = (TextView) rootView
				.findViewById(R.id.hours_this_week);
		flextimeWeekList = (ListView) rootView
				.findViewById(R.id.flextime_overview);
		flextimeWeekList.setEmptyView(rootView.findViewById(R.id.empty));
		updateListView();
		updateWeekView();
		return rootView;
	}

	private void updateWeekView() {
		week.setText("KW " + currentWeek + " " + currentYear);
	}

	private void updateListView() {
		flextimeWeekList.setAdapter(new FlextimeOverviewAdapter(getActivity(),
				getCurrentWeekDays()));
		flextimeWeekList.setOnItemClickListener(ListenerFactory
				.createOnChangeFlextimeListener(this));
		flextimeWeekList.setOnItemLongClickListener(ListenerFactory
				.createDeleteClickListener(this));
		updateHours();
	}

	private void updateHours() {
		long hours = 0;
		for (FlextimeDay day : getCurrentWeekDays()) {
			hours += day.getLenght();
			if (day.getWorkBreaks().isEmpty()) {
				hours -= prefs.getLong("breaktime", 0);
			}
		}
		hoursThisWeekTv.setText(DateHelper.getTimeAsString(hours));

		if (hours < prefs.getLong("hours_per_week", 0)) {
			hoursThisWeekTv.setTextColor(Color.RED);
		} else {
			hoursThisWeekTv.setTextColor(Color.GREEN);
		}
	}

	private List<FlextimeDay> getCurrentWeekDays() {
		return getApp().getCurrentWeekDays(currentWeek, currentYear);
	}

	@Override
	public void delete(FlextimeDay flextimeDay) {
		getApp().delete(flextimeDay);
		updateListView();
	}

	private FlexplanApplication getApp() {
		return ((FlexplanApplication) getActivity().getApplication());
	}

	@Override
	public void initDelete(FlextimeDay flextimeDay) {
		SimpleDialog
				.newInstance(
						ListenerFactory.createFlextimeDeleteListener(this,
								flextimeDay),
						getString(R.string.ask_delete_day)).show(
						getActivity().getSupportFragmentManager(), TAG);
	}

	@Override
	public void initChange(FlextimeDay flextimeDay) {
		getApp().setFlextimeDay(flextimeDay);
		getApp().updateCache();
		((AbstractActivity) getActivity()).startNextActivity(FlextimeDaySetupActivity.class);
	}

}
