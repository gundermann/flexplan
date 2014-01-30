package com.flexplan.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateHelper {

	public static final long DAY_START = 0;
	public static final long DAY_END = 24 * 60 * 60 * 1000;

	public static CharSequence getDateAsString(long time) {
		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMAN);
		return formatter.format(date);
	}

	public static long convertToLongByWeekOfYear(int dayOfWeek, int weekOfYear, int year) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.YEAR, year);
		cal.set(GregorianCalendar.WEEK_OF_YEAR, weekOfYear);
		cal.set(GregorianCalendar.DAY_OF_WEEK, dayOfWeek);
		return cal.getTimeInMillis();
	}
	
	public static long convertToLong(int dayOfMonth, int month, int year) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.YEAR, year);
		cal.set(GregorianCalendar.MONTH, month);
		cal.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
		return cal.getTimeInMillis();
	}

	public static String getCurrentDateAsString() {
		GregorianCalendar cal = new GregorianCalendar(getCurrentYear(), getCurrentMonth(), getCurrentDayOfMonth());
		return getDateAsString(cal.getTimeInMillis()).toString();
	}

	public static int getCurrentYear() {
		GregorianCalendar cal = new GregorianCalendar();
		return cal.get(GregorianCalendar.YEAR);
	}

	public static int getCurrentMonth() {
		GregorianCalendar cal = new GregorianCalendar();
		return cal.get(GregorianCalendar.MONTH);
	}

	public static int getCurrentDayOfMonth() {
		GregorianCalendar cal = new GregorianCalendar();
		return cal.get(GregorianCalendar.DAY_OF_MONTH);
	}

}
