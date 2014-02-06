package com.flexplan.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateHelper {

	public static final long DAY_START = 0;
	public static final long DAY_END = 24 * 60 * 60 * 1000;

	public static String getLongAsString(long time) {
		Date date = new Date(time);
		return getDateAsString(date);
	}
	
	public static String getDateAsString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMAN);
		return formatter.format(date);
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

	public static String getTimeAsString(long time) {
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

	private static String getDayOfWeekAsString(int day) {
		switch (day) {
		case 1:
			return "Sonntag";
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

	public static String getDateByWeekOfYearAsString(int dayOfWeek,
			int weekOfYear, int year) {
		return getDateAsString(getDateByWeekOfYear(dayOfWeek, weekOfYear, year)).toString();
	}

	public static String getDayOfWeekByDateAsString(String date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(date.substring(0, 2)));
		cal.set(GregorianCalendar.MONTH, Integer.parseInt(date.substring(3, 5)));
		cal.set(GregorianCalendar.YEAR, Integer.parseInt(date.substring(6)));
		return getDayOfWeekAsString(cal.get(GregorianCalendar.DAY_OF_WEEK));
	}

}
