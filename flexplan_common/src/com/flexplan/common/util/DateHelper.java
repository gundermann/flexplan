package com.flexplan.common.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

}
