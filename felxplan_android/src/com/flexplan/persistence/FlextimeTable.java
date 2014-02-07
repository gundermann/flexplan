package com.flexplan.persistence;

import android.content.ContentValues;

import com.flexplan.common.business.FlextimeDay;

public class FlextimeTable {

	public static final String TABLE_NAME = "felxtime";
	public static final String DATE = "date";
	public static final String TIME_FROM = "time_from";
	public static final String TIME_TO = "time_to";

	public static String createTable() {
		StringBuilder sb = new StringBuilder();

		sb.append("create table ").append(TABLE_NAME).append(" (").append(DATE)
				.append(" numeric primary key, ").append(TIME_FROM)
				.append(" numeric, ").append(TIME_TO).append(" numeric);");
		return sb.toString();
	}

	public static String dropTable() {
		StringBuilder sb = new StringBuilder();

		sb.append("DROP TABLE IF EXISTS ").append(TABLE_NAME);
		return sb.toString();
	}

	public static ContentValues getContentValues(FlextimeDay flextimeDay) {
		ContentValues values = new ContentValues();
		values.put(DATE, flextimeDay.getDate());
		values.put(TIME_FROM, flextimeDay.getStartTime());
		values.put(TIME_TO, flextimeDay.getEndTime());

		return values;
	}

	public static String[] selectAll() {
		String[] selection = { DATE, TIME_FROM, TIME_TO };
		return selection;
	}

	public static String getDate() {
		return TABLE_NAME + "." + DATE;
	}

	public static String getTimeFrom() {
		return TABLE_NAME + "." + TIME_FROM;
	}

	public static String getTimeTo() {
		return TABLE_NAME + "." + TIME_TO;
	}

	public static String[] selectTimeFrom() {
		String[] selection = { TIME_FROM};
		return selection;
	}
	
	public static String[] selectTimeTo() {
		String[] selection = { TIME_TO};
		return selection;
	}
}
