package com.flexplan.common.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateConverter {

	public static CharSequence convertToDate(long time) {
		Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
                        Locale.GERMAN);
		return formatter.format(date);
	}

}
