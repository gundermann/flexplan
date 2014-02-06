package com.flexplan.persistence;

import android.content.ContentValues;

import com.flexplan.common.business.WorkBreak;

public class BreakTimeTable {

	public static final String TABLE_NAME = "break";
	public static final String ID = "id";
	public static final String DATE = "date";
	public static final String TIME_FROM = "time_from";
	public static final String TIME_TO = "time_to";

	public static String createTable() {
		StringBuilder sb = new StringBuilder();

		sb.append("create table ").append(TABLE_NAME).append(" (").append(ID)
				.append(" integer primary key autoincrement, ").append(DATE)
				.append(" numeric, ").append(TIME_FROM).append(" numeric, ").append(TIME_TO).append(" numeric);");
		return sb.toString();
	}

	public static String dropTable() {
		StringBuilder sb = new StringBuilder();

		sb.append("DROP TABLE IF EXISTS ").append(TABLE_NAME);
		return sb.toString();
	}

	public static String[] selectAll() {
		String[] selection = {ID, DATE, TIME_FROM, TIME_TO};
		return selection;
	}

	public static String getDate() {
		return TABLE_NAME + "." + DATE;
	}

	public static String[] selectTime() {
		String[] selection = {TIME_FROM, TIME_TO};
		return selection;
	}

	public static ContentValues getContentValues(WorkBreak workBreak, String date) {
		ContentValues values = new ContentValues();
		values.put(TIME_FROM, workBreak.getStartTime());
		values.put(TIME_TO, workBreak.getEndTime());
		values.put(DATE, date);
		return values;
	}

}
