//package com.flexplan.setup.breaks;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.view.View;
//import android.widget.TimePicker;
//
//import com.flexplan.R;
//import com.flexplan.R.id;
//import com.flexplan.R.layout;
//import com.flexplan.common.business.WorkBreak;
//import com.flexplan.common.util.DateHelper;
//import com.flexplan.setup.TimeSetup;
//import com.flexplan.util.AbstractDialog;
//import com.flexplan.util.SaveBreakListener;
//
//public class BreakSetupDialog extends AbstractDialog implements TimeSetup {
//
//	private TimePicker timeFrom;
//	private TimePicker timeTo;
//	private WorkBreak workbreak;
//	private BreakSetup setup;
//
//	public static BreakSetupDialog newInstance(BreakSetup setup,
//			WorkBreak workbreak) {
//		BreakSetupDialog dialog = new BreakSetupDialog();
//		dialog.setup = setup;
//		dialog.workbreak = workbreak;
//		return dialog;
//	}
//
//	public View getCurrentView() {
//		View view = getActivity().getLayoutInflater().inflate(
//				R.layout.timerange_picker, null);
//		return view;
//	}
//
//	public void instantiateViews(View view) {
//		timeFrom = (TimePicker) view.findViewById(R.id.timeFrom);
//		timeTo = (TimePicker) view.findViewById(R.id.timeTo);
//		timeFrom.setIs24HourView(true);
//		timeTo.setIs24HourView(true);
//		if (workbreak != null) {
//			timeFrom.setCurrentHour(DateHelper.convertHoursLongToInt(workbreak
//					.getStartTime()));
//			timeTo.setCurrentHour(DateHelper.convertHoursLongToInt(workbreak
//					.getEndTime()));
//			timeFrom.setCurrentMinute(DateHelper
//					.convertMinutesLongToInt(workbreak.getStartTime()));
//			timeTo.setCurrentMinute(DateHelper.convertMinutesLongToInt(workbreak
//					.getEndTime()));
//		}
//	}
//
//	public Dialog buildDialog(View view) {
//		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
//				getActivity());
//		dialogBuilder.setView(view);
//		dialogBuilder.setPositiveButton(android.R.string.ok,
//				new SaveBreakListener(this, setup, workbreak));
//		dialogBuilder.setNegativeButton(android.R.string.cancel, null);
//		return dialogBuilder.create();
//	}
//
//	public long getCurrentStartTime() {
//		return timeFrom.getCurrentHour() * 60 * 60 * 1000
//				+ timeFrom.getCurrentMinute() * 60 * 1000;
//	}
//
//	public long getCurrentEndTime() {
//		return timeTo.getCurrentHour() * 60 * 60 * 1000
//				+ timeTo.getCurrentMinute() * 60 * 1000;
//	}
//
//}
