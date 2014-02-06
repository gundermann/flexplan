package com.flexplan.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateHelper {

	public static final long DAY_START = 0;
	public static final long DAY_END = 24 * 60 * 60 * 1000;

	public static CharSequence getLongAsString(long time) {
		Date date = new Date(time);
		return getDateAsString(date);
	}
	
	public static CharSequence getDateAsString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMAN);
		return formatter.format(date);
	}

	public static long convertToLongByWeekOfYear(int dayOfWeek, int weekOfYear, int year) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.YEAR, year);
		cal.set(GregorianCalendar.WEEK_OF_YEAR, weekOfYear);
		cal.set(GregorianCalendar.DAY_OF_WEEK, dayOfWeek);
		System.out.println(cal.get(GregorianCalendar.DAY_OF_MONTH));
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
		return getDateAsString(cal.getTime()).toString();
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

	public static CharSequence getTimeAsString(long time) {
		long hours = time/(60*60*1000);
		long minutes = (time-(hours*60*60*1000))/(60*1000);
		
		StringBuilder sb = new StringBuilder();
		if(hours<10){
			sb.append("0");
		}
		sb.append(hours).append(":");
		if(minutes<10){
			sb.append("0");
		}
		sb.append(minutes);
		return sb.toString();
	}

	public static String getDayAsString(long timestamp) {
		Date date = new Date(timestamp);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDayOfWeekAsString(cal.get(GregorianCalendar.DAY_OF_WEEK));
	}

	private static String getDayOfWeekAsString(int day) {
		switch (day) {
		case 1:
			return "Montag";
		default:
			break;
		}
		return "nothing";
	}

	public static Date getDateByWeekOfYear(int dayOfWeek, int weekOfYear,
			int year) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.YEAR, year);
		cal.set(GregorianCalendar.WEEK_OF_YEAR, weekOfYear);
		cal.set(GregorianCalendar.DAY_OF_WEEK, dayOfWeek);
		return cal.getTime();
	}

	public static Date convertToDate(long time) {
		return new Date(time);
	}

	public static String getDateByWeekOfYearAsString(int dayOfWeek,
			int weekOfYear, int year) {
		return getDateAsString(getDateByWeekOfYear(dayOfWeek, weekOfYear, year)).toString();
	}

}
