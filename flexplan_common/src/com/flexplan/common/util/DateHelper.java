package com.flexplan.common.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateHelper {

	public static final long DAY_START = 0;
	public static final long DAY_END = 24*60*60*1000;

	public static CharSequence convertToDate(long time) {
		Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
                        Locale.GERMAN);
		return formatter.format(date);
	}

	public static long convertToLong(int dayOfWeek, int weekOfYear, int year) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.YEAR, year);
		cal.set(GregorianCalendar.WEEK_OF_YEAR, weekOfYear);
		cal.set(GregorianCalendar.DAY_OF_WEEK, dayOfWeek);
		return cal.getTimeInMillis();
	}


}
